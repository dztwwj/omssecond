package com.aaa.oms.controller;

import com.aaa.oms.service.CarinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:CarinfoController
 * discription:
 * author:zjf
 * createTime:2018-12-12 15:17
 */
@Controller
@RequestMapping("car")
public class CarinfoController {
    @Autowired
    private CarinfoService carService;


    /**
     * 跳转模拟后台显示,公司所有车辆列表查询
     */
    @RequestMapping("xianshi")
    public String list(){
        return "car/list";
    }
    /**
     * 在后台显示车辆总表,带分页的
     * @param parcarMap
     * @return
     * 2018-7-18下午4:16:08
     */
    @ResponseBody
    @RequestMapping("carlist")
    public Object CarList(@RequestBody Map parcarMap){
        Map map = new HashMap();
        //total为车辆的总数量
        //System.out.println(map);
        map.put("total", carService.getCarCount(parcarMap));
        map.put("pageData", carService.getCarInfoDao(parcarMap));
        return map;
    }
    /**
     * 后台添加方法
     */
    @ResponseBody
    @RequestMapping("add")
    public int addCar(@RequestBody Map map){

        return carService.addCar(map);
    }
    /**
     * 后台更新方法
     */
    @ResponseBody
    @RequestMapping("update")
    public int updatecar(@RequestBody Map map){
        System.out.println("ssss"+map);
        return carService.updateCar(map);
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object deletecar(int id){

       return carService.deleteCar(id);


    }

    /**
     * 多删除方法
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("batchDel")
    public Object batchDel(String ids) {

        return carService.batchDelete(ids);

    }


}
