package com.aaa.oms.controller;

import com.aaa.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:UserController
 * discription:
 * author:LiuQian
 * createTime:2018-12-14 21:57:13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){

        return "user/list";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println("SSSSS"+map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",userService.getPageParam(map));
        resultmap.put("total",userService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     *员工添加
     * @param map
     * @return
     * @RequestBody 该方法接收的数据为josn对象
     * @ResponseBody 该方法返回值为josn对象
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        System.out.println(map);
        return userService.add(map);
    }

    /**
     * 员工更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);

        return userService.update(map);
    }

    /**
     * 员工删除
     * @param id
     * @return
     */
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{EMPNUM}")
    public Object delete(@PathVariable("EMPNUM") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return userService.delete(id);
    }

}
