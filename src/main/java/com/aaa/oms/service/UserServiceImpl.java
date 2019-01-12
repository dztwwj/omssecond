package com.aaa.oms.service;

import com.aaa.oms.dao.UserDao;
import com.aaa.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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
    @Autowired
    private HttpSession session;
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
        User user=(User)session.getAttribute("user");
        map.put("addEmpnumm",user.getEmpnum());
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

    @Override
    public List<Map> selectG(Integer id) {
        return userDao.selectG(id);
    }

    @Override
    public int updateQian(Map map) {
        return userDao.updateQian(map);
    }

    @Override
    public List<Map> selectEvery(Map map) {
        return userDao.selectEvery(map);
    }

    @Override
    public int selectRank(Object pid) {
        return userDao.selectRank(pid);
    }
}
