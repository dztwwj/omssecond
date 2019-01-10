package com.aaa.oms.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:CourseService
 * discription:课程管理服务层
 * author:zhangliang
 * createTime:2018-12-17 19:43
 */

public interface CourseService {
    /**
     * 课程查询
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
     * @param pid
     * @return
     */
    int delete(int pid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);
}

