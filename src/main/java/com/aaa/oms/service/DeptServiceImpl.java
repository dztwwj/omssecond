package com.aaa.oms.service;

import com.aaa.oms.dao.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DeptServiceImpl
 * discription:
 * author:HPY
 * createTime:2018-12-14 14:17
 */
@Service
public class DeptServiceImpl implements DeptService {

    //依赖注入
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return deptDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return deptDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return deptDao.add(map);
    }

    @Override
    public int update(Map map) {
        return deptDao.update(map);
    }

    @Override
    public int delete(int id) {
        return deptDao.delete(id);
    }

    @Override
    public List<Map> getList() {
        return deptDao.getList();
    }
}
