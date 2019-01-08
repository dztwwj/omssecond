package com.aaa.oms.service;

import com.aaa.oms.dao.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:VideoServiceImpl
 * discription:
 * author:zl
 * createTime:2018-12-18 08:41
 */
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoDao videoDao;


    @Override
    public List<Map> getList(Map map) {
        return videoDao.getList(map);
    }

    @Override
    public int add(Map map) {
        return videoDao.add(map);
    }

    @Override
    public int update(Map map) {
        return videoDao.update(map);
    }

    @Override
    public int delete(int vid) {
        return videoDao.delete(vid);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        boolean isDel =true;
        List list = new ArrayList();
        for (String s : idsArray) {
            int i = videoDao.delete(Integer.valueOf(s));
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
