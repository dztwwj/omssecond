package com.aaa.oms.service;

import com.aaa.oms.dao.LeaveDao;

import com.aaa.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:LeaveServiceImpl
 * discription:请假业务层实现类
 * author:LiuQian
 * createTime:2018-12-18 10:34:37
 */
@Service
public class LeaveServiceImpl implements LeaveService {


    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private HttpSession session;
    @Override
    public List<Map> getPageParam(Map map) {
        return leaveDao.getPageParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return leaveDao.getPageCount(map);
    }

    @Override
    public int updateTG(Map map) {
        return leaveDao.updateTG(map);
    }
    @Override
    public int updateTGTo2(Map map) {
        return leaveDao.updateTGTo2(map);
    }

    @Override
    public int updateNoTG(Map map) {
        return leaveDao.updateNoTG(map);
    }

    @Override
    public int add(Map map) {
        User user=(User)session.getAttribute("user");
        map.put("EMPNUM",user.getEmpnum());
        return leaveDao.add(map);
    }
}
