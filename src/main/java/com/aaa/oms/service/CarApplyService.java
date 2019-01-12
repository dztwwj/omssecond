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
     * 后台车辆审核查询,分页显示
     */
    List<Map> getCarMaintenance(Map map);

    /**
     * 后台车辆审核查询
     */
    int getCarCount(Map map);
    /**
     * 前台车辆审核查询,分页显示
     */
    List<Map> getCarMaintenanceQian(Map map);

    /**
     * 前台车辆审核查询
     */
    int getCarCountQian(Map map);

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
     * 审核驳回
     */
    int turn(Map map);

    /**
     * 查询车辆的车牌号
     *
     * @return
     */
    List<Map> getLiscense();
    /**
     * 审核通过
     *
     * @return
     */
    int updateCarToUse(Map map);

}
