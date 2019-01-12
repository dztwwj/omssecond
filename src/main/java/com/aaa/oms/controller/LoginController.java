package com.aaa.oms.controller;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * className:LoginController
 * discription:
 * author:HPY
 * createTime:2018-12-22 09:30
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;



    /**
     * 跳转前台页面
     * @return
     */
    @RequestMapping("/qiantai")
    public String qiantai(){
        return "zhaopin";
    }

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/indexht";
    }


    /**
     * 执行登录操作
     * @param empnum
     * @param epassword
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/indexht")
    public String indexht(String empnum, String epassword, Model model, HttpSession session){
        //使用shiro编写认证操作
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(empnum,epassword);

        //执行登录方法
        try {
            subject.login(token);
            session.setAttribute("userName",token.getUsername());
            User user = loginService.findByName(empnum);//根据员工编号找该用户信息 吧用户信息放入user中
            System.out.println(user.getIseffective());
            if(user.getIseffective()==0){
                session.setAttribute("user",user);
                return "redirect:/index/toHome";
                //登录成功
            }else{
                model.addAttribute("msg","用户名or密码错误");
                return "indexht";
            }
        } catch (UnknownAccountException e) {
            //登录失败:用户不存在
            model.addAttribute("msg","用户名or密码错误");
            return "indexht";
        }catch (IncorrectCredentialsException e){
            //登录失败:密码不存在
            model.addAttribute("msg","用户名or密码错误");
            return "indexht";
        }
    }
}
