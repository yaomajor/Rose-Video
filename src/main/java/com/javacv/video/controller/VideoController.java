package com.javacv.video.controller;

import com.javacv.video.service.IVideoService;
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

    @RequestMapping(value = {"open"})
    @ResponseBody
    public String open(){
        if(videoService.openCamera()){
            return "摄像头成功开启";
        }else{
            return "摄像头开启失败或者摄像头已经开启！";
        }
    }

    @RequestMapping(value = {"start"},method = RequestMethod.GET)
    @ResponseBody
    public String start(@RequestParam String fileName){
        videoService.startRecord(fileName);
        return "已经开始记录";
    }

    @RequestMapping(value = {"stop"})
    @ResponseBody
    public String stop(){
        videoService.stopRecord();
        return "停止记录";
    }

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

    }

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


}
