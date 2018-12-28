package com.aaa.oms.controller;

import com.aaa.oms.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * className:CourseController
 * discription:课程管理控制层
 * author:zhangliang
 * createTime:2018-12-17 19:47
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/tocourse")
    public String toCourse(){
        return "train/course";
    }

    @ResponseBody
    @RequestMapping("/toList")
    public Object toList(@RequestBody Map map){

        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(courseService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     *
     * @param map
     * @return
     * @RequestBody 该方法接收的数据为josn对象
     * @ResponseBody 该方法返回值为josn对象
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        //System.out.println(map);
        return courseService.add(map);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return courseService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{vid}")
    public Object delete(@PathVariable("vid") int vid){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return courseService.delete(vid);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDel/{ids}")
    public Object batchDel(@PathVariable String ids){
        System.out.println(ids);
       return courseService.batchDelete(ids);
    }


}
