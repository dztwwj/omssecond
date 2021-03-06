package com.aaa.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:IndexController
 * discription:
 * author:zz
 * createTime:2018-12-11 11:23
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/toHome")
    public String toHome(){
        return "index";
    }
}
