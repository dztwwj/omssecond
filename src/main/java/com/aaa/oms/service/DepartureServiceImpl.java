package com.aaa.oms.service;

import com.aaa.oms.dao.DepartureDao;
import com.aaa.oms.dao.LeaveDao;
import com.aaa.oms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    @Autowired
    private HttpSession session;
    @Override
    public List<Map> getPageParam(Map map) {
        return departureDao.getPageParam(map);
    }

    @Override
    public int selectCount() {
        Map map = new HashMap();
        User user=(User)session.getAttribute("user");
        map.put("applyEmp",user.getEmpnum());
        return departureDao.selectCount(map);
    }

    @Override
    public int getPageCount(Map map) {
        return departureDao.getPageCount(map);
    }

    @Override
    public int updateTG(Map map) {
        departureDao.updatee(map);
        departureDao.updateEmp(map);
        return departureDao.updateTG(map);
    }
    @Override
    public int updateTo2(Map map) {
        departureDao.updatee(map);

        return departureDao.updateTo2(map);
    }

    @Override
    public int updateNoTG(Map map) {
        departureDao.updatee(map);
        return departureDao.updateNoTG(map);
    }

    @Override
    public int apply(Map map) {
        User user=(User)session.getAttribute("user");
        map.put("EMPNUMQ",user.getEmpnum());

        return departureDao.apply(map);
    }
}
