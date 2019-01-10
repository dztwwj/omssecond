package com.aaa.oms.controller;

import com.aaa.oms.entity.Node;
import com.aaa.oms.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:zz
 * createTime:2018-12-11 10:24
 */
@Controller
@RequestMapping("/power")
public class PowerController {

    //依赖注入
    @Autowired
    private PowerService powerService;

    /**
     * 跳转权限树页面
     * @return
     */
    @RequestMapping("toTree")
    public String toTree(){
        return "power/tree";
    }

    /**
     * 权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree")
    public Object tree(){
        return powerService.getList();
    }

    /**
     * 权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/checktree")
    public Object checktree(@RequestBody Map map){
        System.out.println(map);
       List<Node> treeList= powerService.getChecktree(map);
        return treeList;
    }
    /**
     * 分权保存
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Object save(@RequestBody Map map){
        int result=powerService.savePower(map);
        return result;
    }
}
