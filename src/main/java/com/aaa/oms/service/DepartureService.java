package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:DepartureService
 * discription:离职
 * author:LiuQian
 * createTime:2018-12-18 10:32:16
 */
public interface DepartureService {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageParam(Map map);

    /**
     *根据session的empnum查询当前是否有离职申请
     * @return
     */
    int selectCount();

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 离职通过
     * @param map
     * @return
     */
    int updateTG(Map map);
    /**
     * 一级审核变为二级
     * @param map
     * @return
     */
    int updateTo2(Map map);
    /**
     * 离职驳回
     * @param map
     * @return
     */
    int updateNoTG(Map map);

    /**
     * 申请离职
     * @param map
     * @return
     */
    int apply(Map map);

}
