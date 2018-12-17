package com.aaa.oms.service;

import com.aaa.oms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:UserServiceImpl
 * discription:
 * author:LiuQian
 * createTime:2018-12-14 21:55:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<Map> getPageParam(Map map) {
        return userDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return userDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
        return userDao.add(map);
    }

    @Override
    public int update(Map map) {
        return userDao.update(map);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }
}