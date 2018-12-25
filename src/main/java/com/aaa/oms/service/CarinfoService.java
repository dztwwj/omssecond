package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:CarinfoService
 * discription:
 * author:zjf
 * createTime:2018-12-12 15:25
 */
public interface CarinfoService {
    /**
     * 带分页的显示汽车方法
     */
    List<Map> getCarInfoDao(Map map);
    /**
     *显示汽车总数量
     */
    int getCarCount(Map map);

    /**
     * 汽车的添加方法
     */
    int addCar(Map map);

    /**
     * 汽车更新方法
     */
    int updateCar(Map map);
    /**
     * 汽车删除方法
     * @param id
     */
    int deleteCar(int id);
    /**
     * 验证车牌是否一致
     */
    String getcarnum(Map map);
    /**
     * 多删除
     */
    int batchDelete(String ids);
}
