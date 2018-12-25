package com.aaa.oms.dao;

import com.aaa.oms.entity.Node;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
@CacheNamespace(implementation = RedisCache.class)
public interface TreeDao {
    /**
     * 获取权限数据(所有树节点，但是没个节点根据判断，带上checked:true)
     * @return
     * @Select("select * from tb_power where id in"(select powerid from tb_role_power where roleid(select roleid from emp where adminid=(select id from admin where id=#{userid})))")
     */
    @Select(value = "")
    List<Node> getcheckedList(String id);

    /**
     * 根据ID获取集合对象
     * @param roleid
     * @return
     *
     */
    @Select(value = "select * from power where id in(select powerid from cu_role_power where  roleid=#{roleid})")
    List<Map<String,Object>> getById(int roleid);
    /**
     * 修改
     * @param map
     * @return
     */
    @Update(value = "update power set name=#{NAME},state=#{STATE},iconcls=#{ICONCLS},url=#{URL},parentid=#{PARENTID} where id=#{ID}")
    int update(Map map);
    /**
     * 添加
     * @param map
     * @return
     */
    @Insert(value = "insert into power(id,name,state,iconcls,url,parentid)" +
            "values(seq_power_id.nextval,#{NAME},#{STATE},#{ICONCLS},#{URL},#{PARENTID})")
    int add(Map map);
}
