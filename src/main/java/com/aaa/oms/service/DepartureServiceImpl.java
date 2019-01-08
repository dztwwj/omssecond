package com.aaa.oms.service;

import com.aaa.oms.dao.DepartureDao;
import com.aaa.oms.dao.LeaveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DepartureServiceImpl
 * discription:离职业务层实现类
 * author:LiuQian
 * createTime:2018-12-18 10:34:37
 */
@Service
public class DepartureServiceImpl implements DepartureService {


    @Autowired
    private DepartureDao departureDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return departureDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return departureDao.getPageCount(map);
    }

    @Override
    public int updateTG(Map map) {
        return departureDao.updateTG(map);
    }

    @Override
    public int updateNoTG(Map map) {
        return departureDao.updateNoTG(map);
    }
}
