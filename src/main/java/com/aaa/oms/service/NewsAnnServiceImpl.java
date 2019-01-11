package com.aaa.oms.service;


import com.aaa.oms.dao.NewsAnnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:NewsAnnServiceImpl
 * discription:
 * author:
 * createTime:2018-12-14 14:17
 */
@Service
public class NewsAnnServiceImpl implements NewsAnnService {

    //依赖注入
    @Autowired
    private NewsAnnDao newsAnnDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return newsAnnDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return newsAnnDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return newsAnnDao.add(map);
    }

    @Override
    public int update(Map map) {
        return newsAnnDao.update(map);
    }

    @Override
    public int delete(int naid) {
        return newsAnnDao.delete(naid);
    }
}
