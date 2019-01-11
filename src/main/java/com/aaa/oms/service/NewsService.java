package com.aaa.oms.service;



import java.util.List;
import java.util.Map;

/**
 * className:NewsService
 * discription:
 * author:
 * createTime:2018-12-11 10:24
 */
public interface NewsService {
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
     * 新闻的添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 新闻的更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 根据nid更新删除状态
     * @param NID
     * @return
     */
    int updateNews(int NID);

    /**
     * 根据nid更新发布状态
     * @param NID
     * @return
     */
    int updateCon(int NID);
}
