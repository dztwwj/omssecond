package com.aaa.oms.service;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
public interface EmployService {
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
     * 审核状态通过
     * @param map
     * @return
     */
    Map tgupdate(Map map);

    /**
     * 部门的添加
     * @param map
     * @return
     */
    int add(Map map);


    /**
     * 审核驳回理由
     * @param map
     * @return
     */
    int rsupdate(Map map);

    /**
     * 根据ID查询
     * @param
     * @return
     */
    List<Map> chaxun(Map map);


    List<Map> getdept(Map map);
    /**
     * 部门的更新
     * @param map
     * @return
     */
    //int update(Map map);

    /**
     * 部门的删除
     * @param stid
     * @return
     */
    //int delete(int stid);
}
