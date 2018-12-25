package com.aaa.oms.controller;

import com.aaa.oms.service.CarApplyService;
import com.aaa.oms.service.CarMaintenanceService;
import com.alibaba.druid.sql.PagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
     * 跳转审核历史记录
     * @return
     */
    @RequestMapping("carApplyHis")
    public String carApplylistHistory(){
        return "car/applylistHistory";
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
    @RequestMapping("carApply")
    public Object carmlist(@RequestBody Map parMap){
        // System.out.println("parMap"+parMap);
        Map map = new HashMap();
        System.out.println(parMap);
        map.put("total",carApplyService.getCarCount(parMap));
        map.put("pageData", carApplyService.getCarMaintenance(parMap));
         System.out.println("jjjj"+map);
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

       // System.out.println(map);
        return carApplyService.updateToTG(map);

    }
    @ResponseBody
    @RequestMapping("getCarNumber")
    public Object getCarNumber(@RequestParam Map map){
        //获取本公司的车辆的车牌号,放在一个集合里面
        List<Map> carNumber = carApplyService.getCarNumber(map);
        return carNumber;


    }
    /**
     * 审核驳回方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public Object deletecar(int id){

        return carApplyService.deleteCar(id);
    }
    @ResponseBody
    @RequestMapping("batchDel")
    public Object batchDel(String ids) {

        return carApplyService.batchDelete(ids);

    }
    /**
     * 审核通过方法
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("turnTG")
    public Object turnTG(@RequestBody Map map){
        System.out.println(map);
        return carApplyService.turn(map);

    }
}
