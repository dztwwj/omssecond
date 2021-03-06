package com.aaa.oms.controller;

import com.aaa.oms.service.EmployService;
import com.aaa.oms.util.FtpConfig;
import com.aaa.oms.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * className:EmployController
 * discription:
 * author:HPY
 * createTime:2018-12-17 11:22
 */
@Controller
@RequestMapping("/employ")
public class EmployController {

    //依赖注入ftp工具类
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private FtpConfig ftpConfig;
    @Autowired
    private ResourceLoader resourceLoader;

    //依赖注入
    @Autowired
    private EmployService employService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "/employ/list";
    }

    /**
     * 跳转到审核记录页面
     * @return
     */
    @RequestMapping("/ListRecord")
    public String ListRecord(){
        return "/employ/listrecord";
    }

    /**
     * 跳转到入职新员工页面
     * @return
     */
    @RequestMapping("/entry")
    public String entry(){
        return "/employ/entry";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody//返回json数据
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        System.out.println("aaa"+map);
        Map resultmap = new HashMap();
        resultmap.put("pageData",employService.getPageParam(map));
        resultmap.put("total",employService.getPageCount(map));
        return resultmap;
    }


    @ResponseBody
    @RequestMapping("/tgupdate")
    public Object tgupdate(@RequestBody Map map){
        return employService.tgupdate(map);
    }

    @ResponseBody
    @RequestMapping("/rsupdate")
    public Object rsupdate(@RequestBody Map map){
        return employService.rsupdate(map);
    }

    @ResponseBody
    @RequestMapping("/chaxun")
    public Object chaxun(@RequestBody Map map){
        return employService.chaxun(map);
    }

    @ResponseBody
    @RequestMapping("/deptt")
    public Object getdept(Map map){
        return employService.getdept(map);
    }
    /**
     * 上传方法
     * @param file
     * @return
     */
   @ResponseBody
    @RequestMapping("/upLoadPic")
    public Object upLoadPic(@RequestParam MultipartFile file){
        String s = ftpUtil.upLoad(file);//调用上传方法
        return s;
    }
    /**
     * 显示Ftp图片
     * @param fileName
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            //  ftp://192.168.1.14/98f20a5d-7304-41c7-ac5a-db07d2aaffd3.png
            return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfig.getFtpUserName()+":"+ftpConfig.getFtpPassWord()+"@"+ ftpConfig.getRemoteIp()+ftpConfig.getRemotePath() + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * 入职新员工完善信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/perfect")
    public Object perfectadd(@RequestBody Map map){
        System.out.println(map);
        return employService.perfectadd(map);
    }


    /**
     * 添加应聘人员
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addEmploy")
    public Object addemploy(@RequestBody Map map){
        return employService.addEmploy(map);
    }

    /**
     * 班组查询
     */
    @ResponseBody
    @RequestMapping("/gid")
    public Object getgid(){
        return employService.getgid();
    }

    /**
     * 职称查询
     */
    @ResponseBody
    @RequestMapping("/posid")
    public Object getposid(){
        return employService.getposid();
    }
}
