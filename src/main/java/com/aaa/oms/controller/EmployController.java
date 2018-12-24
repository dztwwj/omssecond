package com.aaa.oms.controller;

import com.aaa.oms.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * className:EmployController
 * discription:
 * author:HPY
 * createTime:2018-12-17 11:22
 */
@Controller
@RequestMapping("/employ")
public class EmployController {

    //依赖注入
    @Autowired
    private EmployService employService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "/employ/list";
    }


    @RequestMapping("/ListRecord")
    public String ListRecord(){
        return "/employ/listrecord";
    }
    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody//返回json数据
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println(map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",employService.getPageParam(map));
        resultmap.put("total",employService.getPageCount(map));
        return resultmap;
    }

    @ResponseBody
    @RequestMapping("/tgupdate")
    public Object tgupdate(@RequestBody Map map){
        System.out.println(map);
        return employService.tgupdate(map);
    }
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        return employService.add(map);
    }

    @ResponseBody
    @RequestMapping("/rsupdate")
    public Object rsupdate(@RequestBody Map map){
        return employService.rsupdate(map);
    }

    @ResponseBody
    @RequestMapping("/chaxun")
    public Object chaxun(@RequestBody Map map){
        return employService.chaxun(map);
    }
}
