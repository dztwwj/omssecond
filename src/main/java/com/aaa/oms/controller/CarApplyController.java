package com.aaa.oms.controller;

import com.aaa.oms.service.CarApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:CarApplyController
 * discription:车辆审核controller
 * author:zjf
 * createTime:2018-12-21 14:44
 */
@Controller
@RequestMapping("CarApply")
public class CarApplyController {
    //依赖注入
    @Autowired
    private CarApplyService carApplyService;

    /**
     * 跳转审核管理
     * @return
     */
    @RequestMapping("car")
    public String carm(){
        return "car/applylist";
    }
    /**
     * 跳转前台申请车辆页面
     * @return
     */
    @RequestMapping("carQian")
    public String carmQian(){
        return "car/applylistQian";
    }
    /**
     * 跳转审核历史记录
     * @return
     */
    @RequestMapping("carApplyHis")
    public String carApplylistHistory(){
        return "car/applylistHistory";
    }


    /**
     * 审核页面查询
     */
    @ResponseBody
    @RequestMapping("carApply")
    public Object carmlist(@RequestBody Map parMap){
        Map map = new HashMap();
        map.put("total",carApplyService.getCarCount(parMap));
        map.put("pageData", carApplyService.getCarMaintenance(parMap));
        return map;
    }
    /**
     * 前台审核页面查询
     */
    @ResponseBody
    @RequestMapping("carApplyQian")
    public Object carmlistQian(@RequestBody Map parMap){
        Map map = new HashMap();
        map.put("total",carApplyService.getCarCountQian(parMap));
        map.put("pageData", carApplyService.getCarMaintenanceQian(parMap));
        return map;
    }
    /**
     * 审核页面数量
     */
    @ResponseBody
    @RequestMapping("add")
    public Object addcarm(@RequestBody Map paraddMap){
        return carApplyService.add(paraddMap);
    }
    /**
     * 审核通过方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("updateTG")
    public Object updateTG(@RequestBody Map map){
        carApplyService.updateCarToUse(map);
        return carApplyService.updateToTG(map);


    }

    /**
     * 审核驳回方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("turnTG")
    public Object turnTG(@RequestBody Map map){

        return carApplyService.turn(map);

    }

    /**
     * 查询车辆的车牌号
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLiscense")
    public List<Map> getLiscense(){
        System.out.println("lsicence"+carApplyService.getLiscense());
        return carApplyService.getLiscense();
    }
}
