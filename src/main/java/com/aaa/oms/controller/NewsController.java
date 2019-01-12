package com.aaa.oms.controller;


import com.aaa.oms.service.NewsService;
import com.aaa.oms.util.FtpConfig;
import com.aaa.oms.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * className:NewsController
 * discription:
 * author:
 * createTime:2018-12-14 14:20
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    //依赖注入
    @Autowired
    private FtpConfig ftpConfig;
    @Autowired
    private FtpUtil ftpUtil;

    //依赖注入
    @Autowired
    private NewsService newsService;

    private ResourceLoader resourceLoader;
    public NewsController(ResourceLoader resourceLoader){
        this.resourceLoader=resourceLoader;
    }
    //取出配置文件中upload.path的值  赋给uploadPath类变量
    @Value(value = "${upload.path}")
    private String uploadPath;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){
        return "news/list";
    }

    /**
     * 跳转新闻页面
     * @return
     */
    @RequestMapping("/aaa")
    public String aaa(){
        return "frontHtml/news/xinwen";
    }

    /**
     * 跳转新闻详情页面
     * @return
     */
    @RequestMapping("/tonewx")
    public String tonewx( Model model,Integer nid){
        System.out.println("nnid"+nid);
        model.addAttribute("nnid",nid);
        return "frontHtml/news/xinwenx";
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
        resultmap.put("pageData",newsService.getPageParam(map));
        resultmap.put("total",newsService.getPageCount(map));//total 当前分页的总数量
        return resultmap;
    }

    /**
     * 通过ni查询nclob
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectNclobByNid")
    public Object selectNclobByNid(@RequestBody Map map){
        return  newsService.selectNclobByNid(map);
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
        return newsService.add(map);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return newsService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{NID}")
    public Object delete(@PathVariable("NID")int NID){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return newsService.updateNews(NID);
    }

    //新闻是否发布
    @ResponseBody//标志返回值是josn
    @RequestMapping("/updat/{NID}")
    public Object updat(@PathVariable("NID")int NID){
        return newsService.updateCon(NID);
    }

    /**
     * 新闻类型查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(Map map){
        return newsService.getList(map);
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
     * 下载方法
     * @param fileName
     * @param response
     */
    @RequestMapping("/downloadPic")
    public void downloadPic(String fileName, HttpServletResponse response){
        ftpUtil.downLoad(fileName,response);
    }

    /**
     * 显示Ftp图片
     * @param fileName
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfig.getFtpUserName()+":"+ftpConfig.getFtpPassWord()+"@"+ ftpConfig.getRemoteIp()+ftpConfig.getRemotePath() + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}












