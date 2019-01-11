package com.aaa.oms.controller;


import com.aaa.oms.service.NewsAnnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * className:NewsAnnController
 * discription:
 * author:HPY
 * createTime:2018-12-14 14:20
 */
@Controller
@RequestMapping("/newsAnn")
public class NewsAnnController{

    //依赖注入
    @Autowired
    private NewsAnnService newsAnnService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "newsAnn/list";
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
        resultmap.put("pageData",newsAnnService.getPageParam(map));
        resultmap.put("total",newsAnnService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * @param map
     * @return
     * @RequestBody 该方法接收的数据为josn对象
     * @ResponseBody 该方法返回值为josn对象
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        System.out.println(map);
        return newsAnnService.add(map);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return newsAnnService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{naid}")
    public Object delete(@PathVariable("naid")int naid){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return newsAnnService.delete(naid);
    }
}












