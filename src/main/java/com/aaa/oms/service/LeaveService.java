package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:LeaveService
 * discription:请假业务层
 * author:LiuQian
 * createTime:2018-12-18 10:32:16
 */
public interface LeaveService {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 请假通过
     * @param map
     * @return
     */
    int updateTG(Map map);
    /**
     * 请假驳回
     * @param map
     * @return
     */
    int updateNoTG(Map map);

}
