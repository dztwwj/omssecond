package com.aaa.oms.controller;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.DepartureService;
import com.aaa.oms.service.LeaveService;
import com.aaa.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    /**
     * 跳转离职审核页面
     * @return
     */
    @RequestMapping("/departureManage")
    public String departureManage(Model model){
        Map map = new HashMap();
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        //System.out.println(map+"map");
       // System.out.println(userService.selectEvery(map).get(0).get("POSITION")+"ssssss");
        Object pid = (userService.selectEvery(map)).get(0).get("POSITION");
        Object gid = (userService.selectEvery(map).get(0).get("GID"));
        //System.out.println("pid"+pid);
//        Map map1 = new HashMap();
//        map1.put("pid",pid);
        int rank = userService.selectRank(pid);
        //System.out.println("rank"+rank);
        if(rank == 2 ){
            model.addAttribute("gid",gid);
            return "departure/departureManage1";
        }else if(rank == 3) {
            model.addAttribute("gid",gid);
            return "departure/departureManage2";
        } else{
            return "departure/departureManage3";
        }

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
    @RequestMapping("/departureApply")
    public String departureWanliu(){
        return "frontHtml/departure/departureApply";
    }
    /**
     * 点击离职判断跳转路径，申请过的话请耐心等待，未申请成功进入挽留页面
     * @return
     */
    @RequestMapping("/departureQian")
    public String departureQian(){
        int count = departureService.selectCount();
        if(count >0){
            return "frontHtml/departure/wait";
        }else{
            return "frontHtml/departure/wanliu";
        }
    }

    /**
     * 离职审核页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        Object gid = (userService.selectEvery(map).get(0).get("GID"));
        map.put("gid",gid);
        System.out.println(gid+"gid");
        Map resultmap = new HashMap();
        resultmap.put("pageData",departureService.getPageParam(map));
        resultmap.put("total",departureService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 离职审核页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object page1(@RequestBody Map map){
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
    }
    /**
     * 呈报，1变为2级
     */
    @ResponseBody
    @RequestMapping("/updateTo2")
    public int updateTo2(@RequestBody Map map){

        return departureService.updateTo2(map);
    }
    /**
     * 离职驳回
     */
    @ResponseBody
    @RequestMapping("/updateNoTG")
    public int updateNoTG(@RequestBody Map map){
       // System.out.println("离职驳回"+map);
        return departureService.updateNoTG(map);
    }
    /**
     * 离职驳回
     */
    @ResponseBody
    @RequestMapping("/apply")
    public int apply(@RequestBody Map map){
        Map map1 = new HashMap();
        User user=(User)session.getAttribute("user");
        map1.put("dempnum",user.getEmpnum());
        //System.out.println(map+"map");
        // System.out.println(userService.selectEvery(map).get(0).get("POSITION")+"ssssss");
        Object pid = (userService.selectEvery(map1)).get(0).get("POSITION");
        Object gid = (userService.selectEvery(map1).get(0).get("GID"));
        System.out.println(gid+"gid");
        //System.out.println("pid"+pid);
//        Map map1 = new HashMap();
//        map1.put("pid",pid);
        int rank = userService.selectRank(pid);
        if(rank == 1){
            map.put("grade",1);
            map.put("gid",gid);
            return departureService.apply(map);
        }else if (rank == 2 ){
            map.put("grade",2);
            map.put("gid",gid);
            return departureService.apply(map);
        }else {
            map.put("grade",3);
            map.put("gid",1);
            return departureService.apply(map);
        }


    }

}
