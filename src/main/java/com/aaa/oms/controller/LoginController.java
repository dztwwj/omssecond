package com.aaa.oms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    /*@ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("LoginController.hello()");
        return "ok";
    }*/

    /*@RequestMapping("/testThymeleaf")
    public String testThymeleaf(){
        return "power/tree";
    }*/
    /**
     * 测试方法
     * @return
     */
    /*@RequestMapping("/add")
    public String add(){
        return "/user/add";
    }*/
    /**
     * 测试方法
     * @return
     */

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "indexht";
    }

    @RequestMapping("/indexht")
    public String indexht(String empnum,String epassword,Model model){
        System.out.println("name="+empnum);
        System.out.println(empnum+"================"+epassword);
        //使用shiro编写认证操作
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(empnum,epassword);

        //执行登录方法
        try {
            subject.login(token);
            return "redirect:/index/toHome";
            //登录成功
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户不存在
            model.addAttribute("msg","用户名不存在");
            return "indexht";
        }catch (IncorrectCredentialsException e){
            //登录失败:密码不存在
            model.addAttribute("msg","密码错误");
            return "indexht";
        }
    }
}
