package com.aaa.oms.service;

import com.aaa.oms.dao.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:positionServiceImpl
 * discription:
 * author:HPY
 * createTime:2019-01-03 15:53
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Override
    public List<Map> getPageParam(Map map) {
        return positionDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return positionDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        System.out.println(map);
        return positionDao.add(map);
    }

    @Override
    public int update(Map map) {
        return positionDao.update(map);
    }

    @Override
    public int delete(int id) {
        return positionDao.delete(id);
    }

    @Override
    public List<Map> getdname() {
        return positionDao.getdname();
    }


}
