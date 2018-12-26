package com.aaa.oms.controller;

import com.aaa.oms.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * className:RecruitController
 * discription:
 * author:HPY
 * createTime:2018-12-15 11:30
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController {

    //依赖注入
    @Autowired
    private RecruitService recruitService;

    /**·
     * 跳转分页列表
     * @param map
     * @return
     */
    @RequestMapping("/toList")
    public Object toList(Map map){
        return "recruit/list";
    }
    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //System.out.println(map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",recruitService.getPageParam(map));
        resultmap.put("total",recruitService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        //System.out.println(map);
        return recruitService.add(map);
    }

    /**
     * 部门的更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return recruitService.update(map);
    }

    /**
     * 部门的删除
     * @param id
     * @return
     */
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable("id") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return recruitService.delete(id);
    }


    @ResponseBody
    @RequestMapping("/chaxun/{id}")
    public Object chaxun(@PathVariable("id") Integer id ){
        //System.out.println(id+"......");
        return recruitService.chaxun(id);
    }
}
