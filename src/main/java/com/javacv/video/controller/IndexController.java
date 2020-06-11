package com.javacv.video.controller;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author 86133
 * @Date 2020/6/1 16:39
 * @Version 1.0
 **/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/")
    public String index(){
        System.out.println("/index");
        return "index";
    }
}