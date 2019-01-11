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
    /**
     * 汽车多删除
     */
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
    /**
     * 带分页的显示汽车方法
     */
    @Override
    public List<Map> getCarInfoDao(Map map) {

        return carinfoDao.getCarInfoDao(map);
    }
    /**
     *显示汽车总数量
     */
    @Override
    public int getCarCount(Map map) {
        return carinfoDao.getCarCount(map);
    }
    /**
     * 汽车的添加方法
     */
    @Override
    public int addCar(Map map) {
        return carinfoDao.addCar(map);
    }
    /**
     * 汽车更新方法
     */
    @Override
    public int updateCar(Map map) {
        return carinfoDao.updateCar(map);
    }
    /**
     * 汽车删除方法
     * @param id
     */
    @Override
    public int deleteCar(int id) {
        return carinfoDao.deleteCar(id);
    }


}
