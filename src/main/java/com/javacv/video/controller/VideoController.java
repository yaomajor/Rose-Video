package com.javacv.video.controller;

import com.javacv.video.service.IVideoService;
import com.javacv.video.vo.ConfigFile;
import com.javacv.video.vo.Success;
import com.javacv.video.vo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName VideoController
 * @Description TODO 包装视频录制服务
 * @Author 86133
 * @Date 2020/5/19 17:19
 * @Version 1.0
 **/
@Controller
@RequestMapping({"/video"})
public class VideoController {

    @Autowired
    private IVideoService videoService;

    /**
     * 打开摄像头
     * @return
     */
    @RequestMapping(value = {"open"})
    @ResponseBody
    public String open(){
        if(videoService.openCamera()){
            return "摄像头成功开启";
        }else{
            return "摄像头开启失败或者摄像头已经开启！";
        }
    }

    /**
     * 开始录制视频
     * @param fileName
     * @return
     */
    @RequestMapping(value = {"start"},method = RequestMethod.GET)
    @ResponseBody
    public String start(@RequestParam String fileName){
        videoService.startRecord(fileName);
        return "已经开始记录";
    }

    /**
     * 停止录制视频
     * @return
     */
    @RequestMapping(value = {"stop"})
    @ResponseBody
    public String stop(){
        videoService.stopRecord();
        return "停止记录";
    }

    /**
     * 获取视频列表
     * @param video
     * @param model
     * @return
     */
    @RequestMapping(value = {"getVideoList"})
    @ResponseBody
    public ModelAndView getVideoList(@ModelAttribute("video")Video video,Model model){
        List<Video> videoList = videoService.getVideoList();
        if(null != videoList){
            String title = "当前视频列表";
            model.addAttribute("videoList",videoList);
            model.addAttribute("title",title);
            return new ModelAndView("videoList","videoModel",model);
        }else{
            return null;
        }
//        return videoList;
    }


    /**
     * 根据单号获取视频
     * @param video
     * @param model
     * @return
     */
    @RequestMapping(value = {"getVideoByCode"},method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView getVideoByCode(@ModelAttribute("video") Video video,Model model){
        String name = video.getName();
        if(null != videoService.getVideoByCode(name)){
            Video returnVideo = videoService.getVideoByCode(name);
            String title = returnVideo.getName();
            model.addAttribute("video",returnVideo);
            model.addAttribute("title",title);
            return new ModelAndView("videoByCode","singleVideoModel",model);
        }else{
            model.addAttribute("title","未查询到该条视频记录！");
            return new ModelAndView("videoNull","videoNullModel",model);
        }

    }

    /**
     * 读取当前的配置文件
     */
    @RequestMapping(value = {"readConfig"},method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView readConfig(Model model){
        ConfigFile cf = videoService.readConfig();
        model.addAttribute("config",cf);
        model.addAttribute("title","配置文件");
        return new ModelAndView("config","configModel",model);
    }

    /**
     * 修改相关配置文件
     */
    @RequestMapping(value = {"updateConfig"},method=RequestMethod.POST)
    @ResponseBody
    public Success updateConfig(@ModelAttribute("config") ConfigFile configFile){
        videoService.updateConfig(configFile);
        Success success = new Success();
        success.setMessage("修改成功");
        success.setSuccess(true);
        return success;
    }

}
