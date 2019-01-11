package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:CarReturnService
 * discription:
 * author:zjf
 * createTime:2018-12-27 12:06
 */
public interface CarReturnService {
    /**
     * 还车查询,分页显示
     */
    List<Map> getCarMaintenance(Map map);

    /**
     * 还车查询
     */
    int getCarCount(Map map);

    /**
     * 还车成功方法
     * @param map
     * @return
     */
    int updateToTG(Map map);





}
