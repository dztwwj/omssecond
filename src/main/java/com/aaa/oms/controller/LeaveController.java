package com.aaa.oms.controller;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.LeaveService;
import com.aaa.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    /**
     * 跳转请假审核页面
     * @return
     */
    @RequestMapping("/leaveManage")
    public String leaveManage(){
        Map map = new HashMap();
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        //System.out.println(map+"map");
        // System.out.println(userService.selectEvery(map).get(0).get("POSITION")+"ssssss");
        Object pid = (userService.selectEvery(map)).get(0).get("POSITION");
        //System.out.println("pid"+pid);
//        Map map1 = new HashMap();
//        map1.put("pid",pid);
        int rank = userService.selectRank(pid);
        //System.out.println("rank"+rank);
        if(rank == 2 ){
            return "leave/leaveManage1";
        }else if(rank == 3) {
            return "leave/leaveManage2";
        } else{
            return "leave/leaveManage3";
        }
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
        return "frontHtml/leave/leaveApply";
    }

    /**
     * 请假审核页面分页
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
        Map resultmap = new HashMap();
        resultmap.put("pageData",leaveService.getPageParam(map));
        resultmap.put("total",leaveService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 请假审核页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object page1(@RequestBody Map map){
        Map resultmap = new HashMap();
        resultmap.put("pageData",leaveService.getPageParam(map));
        resultmap.put("total",leaveService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 前台请假页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQian")
    public Object pageQian(@RequestBody Map map){
        User user=(User)session.getAttribute("user");
        map.put("EMPNUMQ",user.getEmpnum());
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
//        System.out.println("请假通过"+map);
        return leaveService.updateTG(map);
    }
    /**
     * 请假通过
     */
    @ResponseBody
    @RequestMapping("/updateTGTo2")
    public int updateTGTo2(@RequestBody Map map){
//        System.out.println("请假通过"+map);
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

    /**
     * 请假申请添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map) {
        Object leavedaysum = map.get("LEAVEDAYSUM");
        Integer days = Integer.valueOf(leavedaysum+"");
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        //System.out.println(map+"map");
        // System.out.println(userService.selectEvery(map).get(0).get("POSITION")+"ssssss");
        Object pid = (userService.selectEvery(map)).get(0).get("POSITION");
        map.put("pid",pid);
        Object gid = (userService.selectEvery(map).get(0).get("GID"));
        //System.out.println("pid"+pid);
//        Map map1 = new HashMap();
//        map1.put("pid",pid);
        int rank = userService.selectRank(pid);
        if(rank == 1){
            if(days <= 7){
                map.put("gid",gid);
                map.put("grade",1);
                return leaveService.add(map);
            }else{
                map.put("grade",2);
                map.put("gid",gid);
                return leaveService.add(map);
            }
        }else if( rank == 2){
            map.put("grade",2);
            map.put("gid",gid);
            return leaveService.add(map);
        }else{
            map.put("grade",3);
            map.put("gid",1);
            return leaveService.add(map);
        }
    }
}
