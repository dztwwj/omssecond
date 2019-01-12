package com.aaa.oms.controller;

import com.aaa.oms.service.PlanService;
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
 * className:PlanController
 * discription:
 * author:zl
 * createTime:2018-12-24 09:29
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @RequestMapping("toplan")
    public String toPlan(){
        return "train/plan";
    }

    @RequestMapping("toplantwo")
    public String toPlantwo()
    {
        return "frontHtml/train/plan";
    }

    @ResponseBody
    @RequestMapping("/toList")
    public  Object toList(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo<Map> pageInfo=new PageInfo<>(planService.getList(map));
        Map resultMap =new HashMap();
        resultMap.put("pageData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
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
        System.out.println(map);
        return planService.add(map);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return planService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{p_id}")
    public Object delete(@PathVariable("p_id") int p_id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return planService.delete(p_id);
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
        return planService.batchDelete(ids);
    }

    @ResponseBody
    @RequestMapping("/courseList1")
    public  Object couserList1(){
        return  planService.courseList1();
    }

    @ResponseBody
    @RequestMapping("/courseList/{pid}")
    public  Object couserList(@PathVariable("pid")int pid){
        System.out.println(pid);
        return  planService.courseList(pid);
    }

    @ResponseBody
    @RequestMapping("/planList")
    public  Object planList(){
        return  planService.plan(null);
    }
}
