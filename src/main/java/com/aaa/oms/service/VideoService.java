package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:VideoService
 * discription:
 * author:zl
 * createTime:2018-12-18 08:40
 */

public interface VideoService {
    /**
     * 视频查询
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 视频的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 视频的更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 视频的删除
     * @param vid
     * @return
     */
    int delete(int vid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);
}
