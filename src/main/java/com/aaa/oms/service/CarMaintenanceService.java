package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:CarMaintenanceService
 * discription:
 * author:zjf
 * createTime:2018-12-18 09:05
 */
public interface CarMaintenanceService {
    /**
     * 显示数据库的的车辆维修表,分页显示
     */
    List<Map> getCarMaintenance(Map map);

    /**
     * 获取维修车辆总数量
     */
    int getCarCount(Map map);

    /**
     * 添加维修的车辆
     */
    int add(Map map);
    /**
     * 修改维修的车辆,有可能输入错了
     */
    int update(Map map);
    /**
     * 获得车号下拉框
     */
    List<Map> getCarNumber(Map map);

    /**
     * 汽车删除方法
     * @param id
     */
    int deleteCar(int id);
    /**
     * 维修多删除
     */
    int batchDelete(String ids);
}
