package com.aaa.oms.controller;

import com.aaa.oms.service.TrainerService;
import com.aaa.oms.util.FtpConfig;
import com.aaa.oms.util.FtpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * className:TrainerController
 * discription:
 * author:zl
 * createTime:2018-12-20 09:14
 */
@Controller
@RequestMapping("trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private FtpConfig ftpConfig;

    @Autowired
    private FtpUtil ftpUtil;

    private  ResourceLoader resourceLoader;
    public TrainerController(ResourceLoader resourceLoader){
        this.resourceLoader=resourceLoader;
    }
    //取出配置文件中upload.path的值  赋给uploadPath类变量
    @Value(value = "${upload.path}")
    private String uploadPath;

    @RequestMapping("/totrainer")
    public String toTrainer(){
        return "train/trainer";
    }

    @ResponseBody
    @RequestMapping("/trainerList")
    public Object trainerList(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(trainerService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
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
        //System.out.println(map);
        return trainerService.add(map);
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        System.out.println(map);
        return trainerService.update(map);
    }
    @ResponseBody//标志返回值是josn
    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable("id") int id){//@PathVariable可以用来映射URL中的占位符到目标方法的参数中
        return trainerService.delete(id);
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
        return trainerService.batchDelete(ids);
    }

    @ResponseBody
    @RequestMapping("/courseList")
    public  Object couserList(){
        return  trainerService.courseList(null);
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
