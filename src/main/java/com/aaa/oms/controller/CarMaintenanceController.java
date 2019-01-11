package com.aaa.oms.controller;

import com.aaa.oms.service.CarMaintenanceService;
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
 * className:CarMaintenanceController
 * discription:
 * author:zjf
 * createTime:2018-12-18 10:53
 */
@Controller
@RequestMapping("carmainlist")
public class CarMaintenanceController {
    //依赖注入
    @Autowired
    private CarMaintenanceService carMaintenanceService;
    //车辆维修
    @RequestMapping("carm")
    public String carm(){
        return "car/mainlist";
    }
    /**
     * 分页
     * @param map
     * @return
     */

    /**
     * 车辆维修显示
     */
    @ResponseBody
    @RequestMapping("carmlist")
    public Object carmlist(@RequestBody Map parMap){
       // System.out.println("parMap"+parMap);
        Map map = new HashMap();
        map.put("total", carMaintenanceService.getCarCount(parMap));
        map.put("pageData", carMaintenanceService.getCarMaintenance(parMap));
       // System.out.println("jjjj"+map);
        return map;
    }
    /**
     * 维修的车辆  添加列表
     */
    @ResponseBody
    @RequestMapping("add")
    public Object addcarm(@RequestBody Map paraddMap){
//        Map map = new HashMap();
//        try {
//           // map.put("returnVal", true);
//            map.put("msg", "添加成功");
//            carMaintenanceService.add(paraddMap);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            map.put("msg", "添加失败");
//        }
        return carMaintenanceService.add(paraddMap);
    }
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Map map){
//        Map mapupdate = new HashMap();
//        try {
//            carMaintenanceService.update(map);
//            mapupdate.put("returnVal", true);
//            mapupdate.put("errorMsg", "更新成功");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            mapupdate.put("errorMsg", "更新失败");
//        }
        System.out.println(map);
        return carMaintenanceService.update(map);

    }
    @ResponseBody
    @RequestMapping("getCarNumber")
    public Object getCarNumber(@RequestParam Map map){
        //获取本公司的车辆的车牌号,放在一个集合里面
        List<Map> carNumber = carMaintenanceService.getCarNumber(map);
        return carNumber;


    }
    @ResponseBody
    @RequestMapping("delete")
    public Object deletecar(int id){

        return carMaintenanceService.deleteCar(id);
    }
    @ResponseBody
    @RequestMapping("batchDel")
    public Object batchDel(String ids) {

        return carMaintenanceService.batchDelete(ids);

    }
}
