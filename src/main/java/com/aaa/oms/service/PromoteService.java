package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:PromoteService
 * discription:
 * author:LiuQian
 * createTime:2018-12-18 10:32:16
 */
public interface PromoteService {
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
     * 查询审核管理页面
     * @param map
     * @return
     */
    List<Map> auditPromote(Map map);

    /**
     * 查询审核管理总数量
     * @param map
     * @return
     */
    int auditPromoteCount(Map map);
    /**
     * 晋升信息的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 晋升通过
     * @param map
     * @return
     */
    int updateTG(Map map);
    /**
     * 晋升驳回
     * @param map
     * @return
     */
    int updateNoTG(Map map);
    /**
     * 晋升信息的更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 晋升信息的删除
     * @param id
     * @return
     */
    int delete(int id);
    /**
     * 申请晋升
     * @param map
     * @return
     */
    int apply(Map map);
    /**
     * 职位查询
     * @return
     */
    List<Map> selectPosition();

    /**
     * 查询当前empnum有无申请国晋升
     * @return
     */
    List<Map> selectPromote(Map map);
}
