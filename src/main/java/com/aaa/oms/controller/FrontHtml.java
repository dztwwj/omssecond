package com.aaa.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:FrontHtml
 * discription:
 * author:LiuQian
 * createTime:2018-12-25 08:24:49
 */
@Controller
@RequestMapping("/front")
public class FrontHtml {
     @RequestMapping("/toFrontHtml")
    public String toFrontHtml(){
        return "frontHtml/promote/contact";
    }
}
