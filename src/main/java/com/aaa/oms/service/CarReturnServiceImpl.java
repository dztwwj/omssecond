package com.aaa.oms.service;


import com.aaa.oms.dao.CarReturnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:CarReturnServiceImpl
 * discription:
 * author:zjf
 * createTime:2018-12-27 12:07
 */
@Service
public class CarReturnServiceImpl implements CarReturnService{
    @Autowired
    private CarReturnDao carReturnDao;
    /**
     * 还车查询,分页显示
     */
   @Override
    public List<Map> getCarMaintenance(Map map) {
        return carReturnDao.getCarMaintenance(map);
    }
    /**
     * 还车查询
     */
    @Override
    public int getCarCount(Map map) {
        return carReturnDao.getCarCount(map);
    }

    /**
     * 还车成功方法
     * @param map
     * @return
     */
    @Override
    public int updateToTG(Map map) {
        carReturnDao.turnCaraToTG(map);
        return carReturnDao.turnCarToUse(map);
    }


}
