package com.javacv.video.config;


import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.swing.*;

/**
 * @ClassName VideoConfigrucation
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/20 10:06
 * @Version 1.0
 **/
@Configuration
@ComponentScan("com.javacv.video")
@PropertySource("classpath:video.properties")
public class VideoConfiguration {
    @Value("filePath")
    private String filePath;
    @Value("fileName")
    private String fileName;
    /**
     * 创建Grabber对象
     */
    @Bean(name = "grabber")
    public FrameGrabber createGrabber(){
        try{
            return FrameGrabber.createDefault(0);
        }catch(FrameGrabber.Exception e){
            return new OpenCVFrameGrabber(0);
        }
    }
    /**
     *  创建canvas对象
     */
    @Bean(name = "canvas")
    public CanvasFrame createCanvas(){
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        return canvas;
    }
    /**
     * 创建记录record对象(这里初始化recorder对象)
     */
    @Bean(name = "recorder")
    public FrameRecorder createRecord(){
        try{
            FrameRecorder recorder = FrameRecorder.createDefault(fileName, 0, 0);
            return recorder;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
