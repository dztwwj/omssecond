package com.aaa.oms.controller;

import com.aaa.oms.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * className:PromoteController
 * discription:请假
 * author:LiuQian
 * createTime:2018-12-17 21:24:49
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    /**
     * 跳转请假审核页面
     * @return
     */
    @RequestMapping("/leaveManage")
    public String leaveManage(){
        return "leave/leaveManage";
    }
    /**
     * 跳转请假历史记录页面
     * @return
     */
    @RequestMapping("/leaveHistory")
    public String leaveHistory(){
        return "leave/leaveHistory";
    }

    /**
     * 跳转请假前台页面
     * @return
     */
    @RequestMapping("/leaveQian")
    public String leaveQian(){
        return "frontHtml/leave/leaveQian";
    }

    /**
     * 请假审核页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        Map resultmap = new HashMap();
        resultmap.put("pageData",leaveService.getPageParam(map));
        resultmap.put("total",leaveService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 请假通过
     */
    @ResponseBody
    @RequestMapping("/updateTG")
    public int updateTG(@RequestBody Map map){
        System.out.println("请假通过"+map);
        return leaveService.updateTG(map);
    }
    /**
     * 请假驳回
     */
    @ResponseBody
    @RequestMapping("/updateNoTG")
    public int updateNoTG(@RequestBody Map map){
        System.out.println("请假驳回"+map);
        return leaveService.updateNoTG(map);
    }


}
