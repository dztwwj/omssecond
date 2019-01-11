package com.aaa.oms.service;



import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:
 * createTime:2018-12-11 10:24
 */
public interface NewsTypeService {
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
     * 附件的添加
     * @param map
     * @return
     */
    int add(Map map);



    /**
     * 附件的更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 附件的删除
     * @param ncid
     * @return
     */
    int delete(int ncid);
}
