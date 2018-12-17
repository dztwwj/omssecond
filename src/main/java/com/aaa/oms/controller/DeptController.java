package com.aaa.oms.controller;

import com.aaa.oms.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * className:DeptController
 * discription:
 * author:HPY
 * createTime:2018-12-14 14:20
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    //依赖注入
    @Autowired
    private DeptService deptService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "dept/list";
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
        resultmap.put("pageData",deptService.getPageParam(map));
        resultmap.put("total",deptService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 部门列表查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(){
        return deptService.getList();
    }
    /**
     *部门的添加
     * @param map
     * @return
     * @RequestBody 该方法接收的数据为josn对象
     * @ResponseBody 该方法返回值为josn对象
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        //System.out.println(map);
        return deptService.add(map);
    }

    /**
     * 部门的更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        //System.out.println(map);
        return deptService.update(map);
    }

    /**
     * 部门的删除
     * @param id
     * @return
     */
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable("id") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return deptService.delete(id);
    }
}












