package com.wilson.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tag")
public class TagController {

    /**
     * 标签管理
     * @return
     */
    @GetMapping("/")
    public  String Index(){

        return "tag/index";
    }



}
