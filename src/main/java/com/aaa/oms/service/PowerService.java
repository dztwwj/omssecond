package com.aaa.oms.service;

import com.aaa.oms.entity.Node;


import java.util.List;
import java.util.Map;

/**
 * className:PowerService
 * discription:
 * author:zz
 * createTime:2018-12-11 09:35
 */
public interface PowerService {

    /**
     * 列表查询方法
     * @return
     */
    List<Node> getList();

    List<Node> getChecktree(Map map);

    /**
     * 分权保存
     * @param map
     * @return
     */
    int savePower(Map map);
}
