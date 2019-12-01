package com.wm.controller;

import org.springframework.web.bind.annotation.*;

import com.wm.mapper.TodaySpiderDispatcher;

/**
 * Created by CHENT
 */

// @RestController = @Controller + @ ResponseBody
@RestController
@RequestMapping("sdf")
public class HelloController {


    @GetMapping(value = {"hello.do"})
    public  String say (@RequestParam("id") Integer i){

        System.out.println("1111111112222111111111111111");
        System.out.println("1111111111111111111111111");

        return   i.toString();
    }
}