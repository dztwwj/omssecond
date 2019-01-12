package com.aaa.oms.controller;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.PromoteService;

import com.aaa.oms.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PromoteController
 * discription:晋升模块
 * author:LiuQian
 * createTime:2018-12-17 21:24:49
 */
@Controller
@RequestMapping("/promote")
public class PromoteController {

    @Autowired
    private PromoteService promoteService;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    /**
     * 跳转晋升发布页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){

        return "promote/promote";
    }

    /**
     * 跳转晋升发布历史页面
     * @return
     */
    @RequestMapping("/toListInfo")
    public String toListInfo(){

        return "promote/promoteInfo";
    }
    /**
     * 跳转晋升审核页面
     * @return
     */
    @RequestMapping("/auditList")
    public String auditList(){

        return "promote/audit";
    }
    /**
     * 跳转晋升审核历史页面
     * @return
     */
    @RequestMapping("/auditHistory")
    public String auditListInfo(){

        return "promote/auditHistory";
    }

    /**
     * 跳转前台晋升页面
     * @return
     */
    @RequestMapping("/promoteQian")
    public String promoteQian(){
        return "frontHtml/promote/promoteApply";
    }
    /**
     * 跳转等待页面
     * @return
     */
    @RequestMapping("/waitQian")
    public String waitQian(){
        return "frontHtml/promote/wait";
    }


    /**
     * 晋升发布页面分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        Map resultmap = new HashMap();
        resultmap.put("pageData",promoteService.getPageParam(map));
        resultmap.put("total",promoteService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 晋升前台分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object page1(@RequestBody Map map){
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        Object pid = (userService.selectEvery(map)).get(0).get("POSITION");
        int rank = userService.selectRank(pid);
        map.put("rankQian",rank);
        Map resultmap = new HashMap();
        resultmap.put("pageData",promoteService.getPageParam(map));
        resultmap.put("total",promoteService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 审核管理页面审核的分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public Object audit(@RequestBody Map map){
        System.out.println("sss"+map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",promoteService.auditPromote(map));
        resultmap.put("total",promoteService.auditPromoteCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 查询职位
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectPosition")
    public List<Map> selectPosition(){
        return promoteService.selectPosition();
    }

    /**
     * 添加晋升信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int add(@RequestBody Map map){
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());

       // System.out.println(map);
        return promoteService.add(map);
    }
    /**
     * 更新晋升信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public int update(@RequestBody Map map){
        //System.out.println("aaa"+map);
        return promoteService.update(map);
    }
    /**
     * 晋升通过
     */
    @ResponseBody
    @RequestMapping("/updateTG")
    public int updateTG(@RequestBody Map map){
        System.out.println("阿大大"+map);
        return promoteService.updateTG(map);
    }/**
     * 晋升驳回
     */
    @ResponseBody
    @RequestMapping("/updateNoTG")
    public int updateNoTG(@RequestBody Map map){
        System.out.println("驳回"+map);
        return promoteService.updateNoTG(map);
    }
//    /**
//     * 删除晋升信息
//     */
//    @ResponseBody
//    @RequestMapping("/delete/{ID}")
//    public int delete(@PathVariable("ID") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
//        //System.out.println(id+"idididididididididiiddiidid");
//        return promoteService.delete(id);
//    }

    /**
     * 申请晋升
     * @return
     */
    @ResponseBody
    @RequestMapping("/apply")
    public Object apply(@RequestBody Map map){
//        System.out.println(id+"ddddddddddddddddddddd");
        User user=(User)session.getAttribute("user");
        map.put("dempnum",user.getEmpnum());
        List<Map> maps = promoteService.selectPromote(map);
        System.out.println(maps+"maps");
        if(maps.size() == 0){
            return promoteService.apply(map);
        }else{
            return 0;
        }

    }
}
