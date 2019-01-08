package com.aaa.oms.controller;

import com.aaa.oms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:positionController
 * discription:
 * author:HPY
 * createTime:2019-01-03 15:27
 */
@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**·
     * 跳转分页列表
     * @param map
     * @return
     */
    @RequestMapping("/toList")
    public Object toList(Map map){
        return "/positions/list";
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
        resultmap.put("pageData",positionService.getPageParam(map));
        resultmap.put("total",positionService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 职称的添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        System.out.println(map);
        return positionService.add(map);
    }

    /**
     * 职称的更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return positionService.update(map);
    }

    /**
     * 职称的删除
     * @param id
     * @return
     */
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable("id") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return positionService.delete(id);
    }
    /**
     * 根据id查询部门名称
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/dname")
    public Object dname(){
        return positionService.getdname();
    }
}
