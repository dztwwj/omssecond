package com.aaa.oms.service;


import com.aaa.oms.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:NewsServiceImpl
 * discription:
 * author:
 * createTime:2018-12-14 14:17
 */
@Service
public class NewsServiceImpl implements NewsService {

    //依赖注入
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return newsDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return newsDao.getPageCount(map);
    }

    @Override
    public int add(Map map){
        return newsDao.add(map);
    }

    @Override
    public int update(Map map) {
        return newsDao.update(map);
    }

    @Override
    public int updateNews(int NID) {
        return newsDao.updateNews(NID);
    }

    @Override
    public int updateCon(int NID) {
        return newsDao.updateCon(NID);
    }
}
