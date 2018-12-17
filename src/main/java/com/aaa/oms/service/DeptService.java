package com.aaa.oms.service;





import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
public interface DeptService {
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
     * 部门的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 部门的更新
     * @param map
     * @return
     */
   int update(Map map);

    /**
     * 部门的删除
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 部门列表查询
     * @return
     */
    List<Map> getList();
}
