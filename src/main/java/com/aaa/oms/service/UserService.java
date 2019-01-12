package com.aaa.oms.service;

import java.util.List;
import java.util.Map;

/**
 * className:UserService
 * discription:
 * author:LiuQian
 * createTime:2018-12-14 21:53:23
 */
public interface UserService {
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
     * 员工的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 员工的更新
     * @param map
     * @return
     */
    int update(Map map);
    /**
     * 员工自己修改
     * @param map
     * @return
     */
    int updateQian(Map map);

    /**
     * 员工的删除
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据部门id查询对应班组
     * @param id
     * @return
     */
    List<Map> selectG(Integer id);

}
