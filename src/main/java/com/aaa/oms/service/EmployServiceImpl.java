package com.aaa.oms.service;

import com.aaa.oms.dao.EmployDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:EmployServiceImpl
 * discription:
 * author:HPY
 * createTime:2018-12-17 11:20
 */
@Service
public class EmployServiceImpl implements EmployService {

    //依赖注入
    @Autowired
    private EmployDao employDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return employDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return employDao.getPageCount(map);
    }

    @Override
    public int tgupdate(Map map) {
        return employDao.tgupdate(map);
    }

    @Override
    public int add(Map map) {
        System.out.println(map);
        return employDao.add(map);
    }

    @Override
    public int rsupdate(Map map) {
        return employDao.rsupdate(map);
    }

    @Override
    public int chaxun(Map map) {
        return employDao.chaxun(map);
    }
}
