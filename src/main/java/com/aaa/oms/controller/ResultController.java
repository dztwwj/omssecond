package com.aaa.oms.controller;

import com.aaa.oms.service.ResultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:ResultController
 * discription:
 * author:zl
 * createTime:2018-12-21 20:50
 */
@Controller
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @RequestMapping("/toresult")
    public String toresult(){
        return "train/result";
    }

    @ResponseBody
    @RequestMapping("/toList")
    public Object toList(@RequestBody Map map){

        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(resultService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return resultService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{rid}")
    public Object delete(@PathVariable("rid") int rid){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return resultService.delete(rid);
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
        return resultService.batchDelete(ids);
    }

}
