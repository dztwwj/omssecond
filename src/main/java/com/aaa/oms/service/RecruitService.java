package com.aaa.oms.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
public interface RecruitService {
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
     *
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 更新招聘信息
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 删除招聘信息
     * @param id
     * @return
     */
    int delete(int id);


    /**
     * 根据部门ID查询数据
     * @param id
     * @return
     */
    List<Map> chaxun(Integer id);

    /**
     * 审核状态通过
     * @param map
     * @return
     */
    /*Map tgupdate(Map map);*/
}
