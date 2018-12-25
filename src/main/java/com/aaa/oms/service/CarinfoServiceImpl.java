package com.aaa.oms.service;

import com.aaa.oms.dao.CarinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:CarinfoServiceImpl
 * discription:
 * author:zjf
 * createTime:2018-12-12 15:24
 */
@Service
public class CarinfoServiceImpl implements CarinfoService {
    @Autowired
    private CarinfoDao carinfoDao;

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");
        boolean isDel = true;
        List list = new ArrayList();

        for(String s : idsArray){
            list.add(s);
        }
        return carinfoDao.batchDelete(list);

    }

    @Override
    public List<Map> getCarInfoDao(Map map) {

        return carinfoDao.getCarInfoDao(map);
    }

    @Override
    public int getCarCount(Map map) {
        return carinfoDao.getCarCount(map);
    }

    @Override
    public int addCar(Map map) {
        return carinfoDao.addCar(map);
    }

    @Override
    public int updateCar(Map map) {
        return carinfoDao.updateCar(map);
    }

    @Override
    public int deleteCar(int id) {
        return carinfoDao.deleteCar(id);
    }

    @Override
    public String getcarnum(Map map) {
        String suc = "suc";
        //验证车牌号是否一致的方法
        List<Map> getcarnum = carinfoDao.getcarnum(map);
        if(getcarnum!=null&&getcarnum.size()>0){
            suc= "s";
        }
        return suc;
    }
}
