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

    @RequestMapping("/toPowertree")
    public String toPowertree(){
        return "/power/powerList";
    }

    /**
     * 权限树json数据
     * @return
     */
    /*@ResponseBody
    @RequestMapping("/checkedTree")
    public Object checkedTree(String roleid){
        //System.out.println("parentid:"+roleid);
        return treeService.getcheckedList(roleid);
    }*/
    /**
     * 跳转更新页面
     * @param roleid
     * @return
     */
   /* @RequestMapping("/toUpdate")
    public String toUpdate(Integer roleid,Model model){
        model.addAttribute("power", treeService.getById(roleid));
        return "tree/update";
    }*/
    /**
     * 权限菜单修改
     * @param paramMap
     * @param response
     * @throws IOException
     */
   /* @RequestMapping("/update")
    public void update(@RequestParam Map paramMap, HttpServletResponse response) throws IOException{
        int update = treeService.update(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1)
            response.getWriter().print("修改失败！");
        else
            response.getWriter().print("<script>window.parent.parent.location.href=window.parent.parent.location.href; </script>");
    }*/

    /**
     * 权限菜单添加
     * @param paramMap
     * @param response
     * @throws IOException
     */
    /*@RequestMapping("/add")
    public void add(@RequestParam Map paramMap,HttpServletResponse response) throws IOException{
        int update = treeService.add(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1)
            response.getWriter().print("添加失败！");
        else
            response.getWriter().print("<script>window.parent.parent.location.href=window.parent.parent.location.href; </script>");
    }*/

    /**
     * 父节点查询
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/fucha")
    public Object fucha(){
        //System.out.println();
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
        System.out.println(map);
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
        System.out.println(map);
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
        System.out.println(map);
        return treeService.delete(map);
    }

}
