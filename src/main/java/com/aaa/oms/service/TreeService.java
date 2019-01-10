package com.aaa.oms.service;

import com.aaa.oms.entity.Node;

import java.util.List;
import java.util.Map;

/**
 * className:TreeService
 * discription:
 * author:HPY
 * createTime:2018-12-23 16:12
 */
public interface TreeService {
    /**
     * 获取权限数据(所有树节点，但是没个节点根据判断，带上checked:true)
     * @return
     */
    List<Node> getcheckedList(String id);

    /**
     * 根据ID获取集合对象
     * @param parentid
     * @return
     */
    List<Map<String,Object>> getById(int roleid);
    /**
     * 修改
     * @param map
     * @return
     */
    int update(Map map);
    /**
     * 添加
     * @param map
     * @return
     */
    int add(Map map);
}
