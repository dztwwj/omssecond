package com.aaa.oms.service;

import com.aaa.oms.dao.LoginDao;
import com.aaa.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className:LoginServiceImpl
 * discription:
 * author:HPY
 * createTime:2018-12-22 11:26
 */
@Service
public class LoginServiceImpl implements LoginService {

    //依赖注入
    @Autowired
    private LoginDao loginDao;

    @Override
    public User findByName(String empnum) {
        return loginDao.findByName(empnum);
    }

   /* @Override
    public List<User> findByName(String empnum) {
        return loginDao.findByName(empnum);
    }

    @Override
    public List<User> findByid(Integer eid) {
        return loginDao.findByid(eid);
    }*/
}
