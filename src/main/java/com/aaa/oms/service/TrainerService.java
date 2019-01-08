package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:TrainerService
 * discription:
 * author:zl
 * createTime:2018-12-20 09:10
 */

public interface TrainerService {


    /**
     * 培训师查询
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 培训师添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 视培训师更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 培训师删除
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);


    /**\
     * 查询课程
     * @return
     */
    List<Map> courseList(Map map);
}
