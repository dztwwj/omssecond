package com.aaa.oms.service;


import com.aaa.oms.dao.NewsTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:NewsTypeServiceImpl
 * discription:
 * author:
 * createTime:2018-12-14 14:17
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService{

    //依赖注入
    @Autowired
    private NewsTypeDao newsTypeDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return newsTypeDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return newsTypeDao.getPageCount(map);
    }

    @Override
    public int add(Map map){
        return newsTypeDao.add(map);
    }

    @Override
    public int update(Map map) {
        return newsTypeDao.update(map);
    }

    @Override
    public int delete(int ncid) {
        return newsTypeDao.delete(ncid);
    }
}
