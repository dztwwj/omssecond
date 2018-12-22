package com.aaa.oms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * className:LoginController
 * discription:
 * author:HPY
 * createTime:2018-12-22 09:30
 */
@Controller
public class LoginController {

    /**
     * 测试方法
     * @return
     */
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("LoginController.hello()");
        return "ok";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        //吧数据存入model
        model.addAttribute("name","黑马程序员");
        //返回test.html
        return "user/test";
    }
}
