package com.aaa.oms.service;

import com.aaa.oms.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:CourseServiceImpl
 * discription:课程管理服务层实现类
 * author:zl
 * createTime:2018-12-17 19:44
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
   private CourseDao courseDao;

    @Override
    public List<Map> getList(Map map) {
        return courseDao.getList(map);
    }

    @Override
    public int add(Map map) {
        return courseDao.add(map);
    }

    @Override
    public int update(Map map) {
        return courseDao.update(map);
    }

    @Override
    public int delete(int pid) {
        return courseDao.delete(pid);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        boolean isDel =true;
        List list = new ArrayList();
        for (String s : idsArray) {
            int i = courseDao.delete(Integer.valueOf(s));
            if(i<1){
                isDel=false;
            }
        }
        if(isDel)
            return 1;
        else
            return 0;
    }
}
