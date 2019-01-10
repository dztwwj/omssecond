package com.aaa.oms.controller;

import com.aaa.oms.service.DepartureService;
import com.aaa.oms.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:DepartureController
 * discription:离职
 * author:LiuQian
 * createTime:2018-12-17 21:24:49
 */
@Controller
@RequestMapping("/departure")
public class DepartureController {

    @Autowired
    private DepartureService departureService;

    /**
     * 跳转离职审核页面
     * @return
     */
    @RequestMapping("/departureManage")
    public String departureManage(){
        return "departure/departureManage";
    }
    /**
     * 跳转离职历史页面
     * @return
     */
    @RequestMapping("/departureHistory")
    public String departureHistory(){
        return "departure/departureHistory";
    }
    /**
     * 跳转离职前台页面
     * @return
     */
    @RequestMapping("/departureQian")
    public String departureQian(){
        return "frontHtml/departure/departureApply";
    }

    /**
     * 离职审核页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println("sss"+map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",departureService.getPageParam(map));
        resultmap.put("total",departureService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 离职通过
     */
    @ResponseBody
    @RequestMapping("/updateTG")
    public int updateTG(@RequestBody Map map){
        System.out.println("离职通过"+map);
        return departureService.updateTG(map);
    }/**
     * 离职驳回
     */
    @ResponseBody
    @RequestMapping("/updateNoTG")
    public int updateNoTG(@RequestBody Map map){
        System.out.println("离职驳回"+map);
        return departureService.updateNoTG(map);
    }

}
