package com.aaa.oms.service;

import com.aaa.oms.dao.CarMaintenanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:CarMaintenanceServiceImpl
 * discription:
 * author:zjf
 * createTime:2018-12-18 09:06
 */
@Service
public class CarMaintenanceServiceImpl implements CarMaintenanceService {
    @Autowired
    private CarMaintenanceDao carMaintenance;
    @Override
    public List<Map> getCarMaintenance(Map map) {

        return carMaintenance.getCarMaintenance(map);
    }

    @Override
    public int getCarCount(Map map) {
        return carMaintenance.getCarCount(map);
    }

    @Override
    public int add(Map map) {
        return carMaintenance.add(map) ;
    }

    @Override
    public int update(Map map) {
        return carMaintenance.update(map);
    }

    @Override
    public List<Map> getCarNumber(Map map) {
        return carMaintenance.getCarNumber(map);
    }

    @Override
    public int deleteCar(int id) {
        return carMaintenance.deleteCar(id);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");
        boolean isDel = true;
        List list = new ArrayList();

        for(String s : idsArray){
            list.add(s);
        }
        return carMaintenance.batchDelete(list);

    }
}
