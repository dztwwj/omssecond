package com.aaa.oms.service;

import com.aaa.oms.dao.EmployDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map tgupdate(Map map) {
        Map map1 = new HashMap();
        map1.put("a",employDao.tgupdate1(map));
        map1.put("b",employDao.tgupdate(map));
        return map1;
    }

    /*@Override
    public int add(Map map) {
        System.out.println(map);
        return employDao.add(map);
    }*/

    @Override
    public int rsupdate(Map map) {
        return employDao.rsupdate(map);
    }

    @Override
    public List<Map> chaxun(Map map) {
        return employDao.chaxun(map);
    }

    @Override
    public List<Map> getdept(Map map) {
        return employDao.getdept(map);
    }

    @Override
    public int perfectadd(Map map) {
        return employDao.perfectadd(map);
    }
    @Override
    public int addEmploy(Map map) {
        return employDao.addEmploy(map);
    }
}
