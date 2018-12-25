package com.aaa.oms.service;

import com.aaa.oms.dao.TreeDao;
import com.aaa.oms.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:TreeServiceImpl
 * discription:
 * author:HPY
 * createTime:2018-12-23 16:13
 */
@Service
public class TreeServiceImpl implements TreeService {

    //依赖注入
    @Autowired
    private TreeDao treeDao;


    @Override
    public List<Node> getcheckedList(String id) {
        return treeDao.getcheckedList(id);
    }

    @Override
    public List<Map<String, Object>> getById(int roleid) {
        return treeDao.getById(roleid);
    }

    @Override
    public int update(Map map) {
        return treeDao.update(map);
    }

    @Override
    public int add(Map map) {
        return treeDao.add(map);
    }
}
