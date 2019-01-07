package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
public interface PositionService {
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
     * 职称的添加
     * @param map
     * @return
     */
    int add(Map map);


    /**
     * 更新职称信息
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 删除职称信息
     * @param id
     * @return
     */
    int delete(int id);
    /**
     * 根据id查询部门名称
     * @param
     * @return
     */
    List<Map> getdname();
}
