package com.aaa.oms.service;

import com.aaa.oms.dao.RecruitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RecruitServiceImpl
 * discription:
 * author:HPY
 * createTime:2018-12-15 11:26
 */
@Service
public class RecruitServiceImpl implements RecruitService {

    //依赖注入
    @Autowired
    private RecruitDao recruitDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return recruitDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return recruitDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return recruitDao.add(map);
    }

    @Override
    public int update(Map map) {
        return recruitDao.update(map);
    }

    @Override
    public int delete(int id) {
        return recruitDao.delete(id);
    }

    @Override
    public List<Map> chaxun(Integer id) {
        return recruitDao.chaxun(id);
    }


}
