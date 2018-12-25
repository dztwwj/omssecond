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

    @Override
    public List<Map> getCarMaintenance(Map map) {
        return carApplyDao.getCarMaintenance(map);
    }

    @Override
    public int getCarCount(Map map) {
        return carApplyDao.getCarCount(map);
    }

    @Override
    public int add(Map map) {
        return carApplyDao.add(map);
    }


    @Override
    public List<Map> getCarNumber(Map map) {
        return carApplyDao.getCarNumber(map);
    }

    @Override
    public int deleteCar(int id) {
        return carApplyDao.deleteCar(id);
    }



    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");
        boolean isDel = true;
        List list = new ArrayList();

        for(String s : idsArray){
            list.add(s);
        }
        return carApplyDao.batchDelete(list);

    }

    @Override
    public int updateToTG(Map map) {
        carApplyDao.updateCarToUse(map);
        return carApplyDao.updateCaraToTG(map);
    }
    @Override
    public int turn(Map map) {
        carApplyDao.turnCarToUse(map);
        return carApplyDao.turnCaraToTG(map);
    }
}
