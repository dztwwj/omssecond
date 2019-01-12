package com.aaa.oms.service;

import com.aaa.oms.dao.CarApplyDao;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:CarApplyServiceImpl
 * discription:
 * author:zjf
 * createTime:2018-12-21 14:51
 */
@Service
public class CarApplyServiceImpl implements CarApplyService {
    @Autowired
    private CarApplyDao carApplyDao;
    /**
     * 后台车辆审核查询,分页显示
     */
    @Override
    public List<Map> getCarMaintenance(Map map) {
        return carApplyDao.getCarMaintenance(map);
    }
    /**
     * 后台车辆审核查询
     */
    @Override
    public int getCarCount(Map map) {
        return carApplyDao.getCarCount(map);
    }
    /**
     * 前台车辆审核查询,分页显示
     */
    @Override
    public List<Map> getCarMaintenanceQian(Map map) {
        return carApplyDao.getCarMaintenanceQian(map);
    }
    /**
     * 前台车辆审核查询
     */
    @Override
    public int getCarCountQian(Map map) {
        return carApplyDao.getCarCountQian(map);
    }

    @Override
    public String getDname(String position) {
        return carApplyDao.getDname(position);
    }

    /**
     * 添加维修的车辆
     */
    @Override
    public int add(Map map) {
        return carApplyDao.add(map);
    }




    /**
     * 审核通过方法
     * @param map
     * @return
     */
    @Override
    public int updateToTG(Map map) {
        carApplyDao.updateCarToUse(map);
        return carApplyDao.updateCaraToTG(map);
    }
    /**
     * 审核驳回
     */
    @Override
    public int turn(Map map) {
        carApplyDao.turnCarToUse(map);
        return carApplyDao.turnCaraToTG(map);
    }
    /**
     * 审核通过方法
     * @param map
     * @return
     */

    @Override
    public int updateCarToUse(Map map) {
        return carApplyDao.updateCarToUse(map);
    }
    /**
     * 查询车辆的车牌号
     *
     * @return
     */
    @Override
    public List<Map> getLiscense() {
        return carApplyDao.getLiscense();
    }
}
