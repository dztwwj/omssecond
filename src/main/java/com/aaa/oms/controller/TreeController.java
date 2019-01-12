package com.aaa.oms.controller;

import com.aaa.oms.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * className:TreeController
 * discription:
 * author:HPY
 * createTime:2018-12-23 16:17
 */
@Controller
@RequestMapping("/treee")
public class TreeController {

    //依赖注入
    @Autowired
    private TreeService treeService;


    /**
     * 权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/powertree")
    public Object tree(){
        return treeService.getpowertree();
    }

    /**
     * 跳转到权限页面
     * @return
     */
    @RequestMapping("/toPowertree")
    public String toPowertree(){
        return "/power/powerList";
    }
    /**
     * 父节点查询
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/fucha")
    public Object fucha(){
        return treeService.getfucha();
    }

    /**
     * 节点的添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        return treeService.add(map);
    }

    /**
     * 节点的更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        return treeService.update(map);
    }

    /**
     * 节点的删除
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody Map map){
        return treeService.delete(map);
    }

}
