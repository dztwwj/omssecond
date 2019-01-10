package com.aaa.oms.service;

import com.aaa.oms.dao.PromoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PromoteServiceImpl
 * discription:
 * author:LiuQian
 * createTime:2018-12-18 10:34:37
 */
@Service
public class PromoteServiceImpl implements PromoteService {


    @Autowired
    private PromoteDao promoteDao;
    @Override
    public List<Map> getPageParam(Map map) {
        return promoteDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return promoteDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return promoteDao.add(map);
    }

    @Override
    public int update(Map map) {
        return promoteDao.update(map);
    }

    @Override
    public int delete(int id) {
        return promoteDao.delete(id);
    }

    @Override
    public List<Map> selectPosition() {
        return promoteDao.selectPosition();
    }
    @Override
    public List<Map> auditPromote(Map map) {
        return promoteDao.auditPromote(map);
    }

    @Override
    public int auditPromoteCount(Map map) {
        return promoteDao.auditPromoteCount(map);
    }
}
