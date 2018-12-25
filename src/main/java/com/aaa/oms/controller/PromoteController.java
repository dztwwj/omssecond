package com.aaa.oms.controller;

import com.aaa.oms.service.PromoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PromoteController
 * discription:
 * author:LiuQian
 * createTime:2018-12-17 21:24:49
 */
@Controller
@RequestMapping("/promote")
public class PromoteController {

    @Autowired
    private PromoteService promoteService;

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
     * 跳转晋升发布历史页面
     * @return
     */
    @RequestMapping("/auditList")
    public String auditList(){

        return "promote/audit";
    }


    /**
     * 分页
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
     * 审核管理页面审核的分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public Object audit(@RequestBody Map map){
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
    /**
     * 删除晋升信息
     */
    @ResponseBody
    @RequestMapping("/delete/{ID}")
    public int delete(@PathVariable("ID") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        //System.out.println(id+"idididididididididiiddiidid");
        return promoteService.delete(id);
    }
}
