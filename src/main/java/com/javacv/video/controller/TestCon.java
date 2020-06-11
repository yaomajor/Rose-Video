package com.javacv.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName TestCon
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/19 16:49
 * @Version 1.0
 **/
@Controller
@RequestMapping({"/test"})
public class TestCon {
    @RequestMapping(value = {"1"})
    public void test(){
        System.out.println("123");
    }
}
