package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:PlanService
 * discription:
 * author:zl
 * createTime:2018-12-24 09:25
 */

public interface PlanService {

    /**
     * 计划与与实施的查询
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 课程的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 课程的更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 课程的删除
     * @param p_id
     * @return
     */
    int delete(int p_id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);

    List<Map> courseList1();

    /**\
     * 查询教师
     * @return
     */
    List<Map> courseList(int pid);

    /**\
     *
     * @return
     */
    List<Map> plan(Map map);
}
