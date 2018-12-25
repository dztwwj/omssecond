package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:CarApplyService
 * discription:
 * author:zjf
 * createTime:2018-12-21 14:49
 */
public interface CarApplyService {
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
     * 审核通过方法
     * @param map
     * @return
     */
    int updateToTG(Map map);
    /**
     * 获得车号下拉框
     */
    List<Map> getCarNumber(Map map);

    /**
     * 审核驳回方法
     * @param map
     * @return
     */
    int deleteCar(int id);
    /**
     * 维修多删除
     */


    int batchDelete(String ids);
    /**
     * 审核驳回
     */
    int turn(Map map);

}
