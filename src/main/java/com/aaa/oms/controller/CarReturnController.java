package com.aaa.oms.controller;


import com.aaa.oms.service.CarReturnService;
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
 * className:CarReturnController
 * discription:
 * author:zjf
 * createTime:2018-12-27 12:05
 */
@Controller
@RequestMapping("CarReturn")
public class CarReturnController {
    //依赖注入
    @Autowired
    private CarReturnService carReturnService;

    /**
     * 跳转还车管理
     *
     * @return
     */
    @RequestMapping("car")
    public String carm() {
        return "car/return";
    }



    /**
     * 还车页面查询
     */
    @ResponseBody
    @RequestMapping("carApply")
    public Object carmlist(@RequestBody Map parMap) {
        Map map = new HashMap();
        map.put("total", carReturnService.getCarCount(parMap));
        map.put("pageData", carReturnService.getCarMaintenance(parMap));
        return map;
    }
    /**
     * 还车成功方法
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("updateTG")
    public Object updateTG(@RequestBody Map map) {

        return carReturnService.updateToTG(map);

    }


}
