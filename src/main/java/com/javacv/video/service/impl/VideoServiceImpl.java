package com.javacv.video.service.impl;

import com.javacv.video.service.IVideoService;
import com.javacv.video.utils.PropertyUtil;
import com.javacv.video.utils.SmbFileList;
import com.javacv.video.vo.ConfigFile;
import com.javacv.video.vo.Video;
import io.micrometer.core.instrument.util.StringUtils;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_objdetect;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;


/**
 * @ClassName VideoServiceImpl
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/19 17:16
 * @Version 1.0
 **/
@Service
@PropertySource("classpath:video.properties")
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private FrameGrabber grabber;//摄像头数据获取--单例
    @Autowired
    private CanvasFrame canvas;//新建窗口单例
    @Autowired
    private FrameRecorder recorder;//recorder
    @Value("${server_address}")
    private String serverAddress;
    @Value("${overtime}")
    private String overtime;
    //设置标识是否已经打开摄像头
    private Boolean flag = false;

    @Override
    public List<Video> getVideoList() {
        return SmbFileList.getFileList();
    }

    @Override
    public Boolean openCamera() {
        if(null == grabber){
            System.out.println("摄像头实例未初始化！");
            return false;
        }else{
            if(flag){
                System.out.println("摄像头已开启！");
                return false;
            }else{
                new Thread(()->{
                    try{
                        grabber.start();   //开始获取摄像头数据
                        while(true) {
                            if (!canvas.isDisplayable()) {//窗口是否关闭
                                //grabber.stop();//停止抓取 这里会导致整个程序停止  所以这里只break。
                                break;
                            }
                            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
                            Thread.sleep(50);//50毫秒刷新一次图像
                        }
                    }catch( InterruptedException e){
                        System.out.println(e.getStackTrace());
                    }catch (FrameGrabber.Exception e){
                        System.out.println(e.getStackTrace());
                    }
                }).start();
                flag = true;
                return true;
            }
        }
    }

    @Override
    public void startRecord(String fileName) {
//      String fileName = VideoStringUtils.getRandomString(5)+".mp4"; 测试生成录制文件名称
        fileName = "rtmp:"+serverAddress+"/live/"+fileName+" live=1";
        try{
            Loader.load(opencv_objdetect.class);
            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();//转换器
            IplImage grabbedImage = converter.convert(grabber.grab());//抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
            int width = grabbedImage.width();
            int height = grabbedImage.height();
            recorder = FrameRecorder.createDefault(fileName, width, height);
            //每次调用录制方法之前先进行stop方法的调用，结束上一段视频的录制
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264，编码
            recorder.setFormat("flv");//封装格式，如果是推送到rtmp就必须是flv封装格式
            recorder.setFrameRate(25);
            recorder.start();//开启录制器
            long startTime=0;
            long videoTS=0;
            canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            canvas.setAlwaysOnTop(true);
            Frame rotatedFrame = converter.convert(grabbedImage); //不知道为什么这里不做转换就不能推到rtmp
            while (canvas.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null ) {
                if(-1 == recorder.getFrameRate()){//调用stop方法后，跳出循环
                    break;
                }
                rotatedFrame = converter.convert(grabbedImage);
                canvas.showImage(rotatedFrame);
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                }
                //进行录制时间判断 达到限定时间自动进行stop方法的调用
                long totalSeconds = (System.currentTimeMillis() - startTime)/1000;
                if(totalSeconds >= Long.valueOf(overtime)){
                    recorder.setFrameRate(-1);//这里设置FrameRate用于判断start方法  进而退出录制循环
                    recorder.stop();
                }
                videoTS = 1000 * (System.currentTimeMillis() - startTime);
                recorder.setTimestamp(videoTS);
                recorder.record(rotatedFrame);
                Thread.sleep(40);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stopRecord() {
        try{
            recorder.setFrameRate(-1);//这里设置FrameRate用于判断start方法  进而退出录制循环
            recorder.stop();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Video getVideoByCode(String name) {
        return SmbFileList.getFileByCode(name);
    }

    @Override
    public ConfigFile readConfig() {
        ConfigFile cf = new ConfigFile();
        cf.setOvertime(PropertyUtil.getProperty("overtime"));
        cf.setServerAddress(PropertyUtil.getProperty("server_address"));
        cf.setServerFilePath(PropertyUtil.getProperty("server_filePath"));
        cf.setSharedFolder(PropertyUtil.getProperty("sharedFolder"));
        cf.setUserAccount(PropertyUtil.getProperty("USER_ACCOUNT"));
        cf.setUserPassword(PropertyUtil.getProperty("USER_PWS"));
        return cf;
    }

    @Override
    public void updateConfig(ConfigFile configFile) {
        if(StringUtils.isNotBlank(configFile.getOvertime())){
            PropertyUtil.setProperty("overtime",configFile.getOvertime());
        }
        if(StringUtils.isNotBlank(configFile.getServerAddress())){
            PropertyUtil.setProperty("server_address",configFile.getServerAddress());
        }
        if (StringUtils.isNotBlank(configFile.getServerFilePath())) {
            PropertyUtil.setProperty("server_filePath",configFile.getServerFilePath());
        }
        if (StringUtils.isNotBlank(configFile.getSharedFolder())) {
            PropertyUtil.setProperty("sharedFolder",configFile.getSharedFolder());
        }
        if (StringUtils.isNotBlank(configFile.getUserAccount())) {
            PropertyUtil.setProperty("USER_ACCOUNT",configFile.getUserAccount());
        }
        if (StringUtils.isNotBlank(configFile.getUserPassword())) {
            PropertyUtil.setProperty("USER_PWS",configFile.getUserPassword());
        }
    }
}





