package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:ResultService
 * discription:
 * author:zl
 * createTime:2018-12-21 20:48
 */

public interface ResultService {

    /**
     * 考核结果查询
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 考核结果更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 考核结果删除
     * @param rid
     * @return
     */
    int delete(int rid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);

}
