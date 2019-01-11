package com.aaa.oms.controller;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession session;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){

        return "user/list";
    }
    /**
     * 跳转前台个人中心页面
     * @return
     */
    @RequestMapping("/toListQian")
    public String toListQian(){

        return "frontHtml/user/userQian";
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
        resultmap.put("pageData",userService.getPageParam(map));
        resultmap.put("total",userService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }
    /**
     * 前台个人中心分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQian")
    public Object pageQian(@RequestBody Map map){
        User user=(User)session.getAttribute("user");
        map.put("empnumQian",user.getEmpnum());

        Map resultmap = new HashMap();
        resultmap.put("pageData",userService.getPageParam(map));
        resultmap.put("total",userService.getPageCount(map));//total 当前分页的总数量
        resultmap.put("sessionEname",user.getEname());
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
        //System.out.println(map);

        return userService.update(map);
    }
    /**
     * 员工自己更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateQian")
    public Object updateQian(@RequestBody Map map){
        //System.out.println(map);

        return userService.updateQian(map);
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

    /**
     * 根据部门id查询对应班组
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectG/{id}")
    public Object selectG(@PathVariable("id") Integer id ){

        return userService.selectG(id);
    }

}
