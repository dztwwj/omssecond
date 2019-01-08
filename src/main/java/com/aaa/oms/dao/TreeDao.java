package com.aaa.oms.dao;

import com.aaa.oms.entity.Node;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
@Component
@CacheNamespace(implementation = RedisCache.class)
public interface TreeDao {


    /**
     * 根据到登录角色不同显示不同的权限树
     * @return
     */
    @Select("select id, name as label, iconcls as iconClass, url, parentid pid from power where id " +
            "in(select powerid from cu_role_power where posid = " +
            "(select position from cu_emp where empnum=#{empnum}))")
    List<Node> getpowertree(Integer empnum);


    /**
     * 获取权限数据(所有树节点，但是没个节点根据判断，带上checked:true)
     * @return
     * @Select("select * from tb_power where id in"(select powerid from tb_role_power where roleid(select roleid from emp where adminid=(select id from admin where id=#{userid})))")
     */
    /*@Select(value = "")
    List<Node> getcheckedList(String id);*/

    /**
     * 根据ID获取集合对象
     * @param
     * @return
     *
     */
    /*@Select(value = "select * from power where id in(select powerid from cu_role_power where  roleid=#{roleid})")
    List<Map<String,Object>> getById(int roleid);*/

    /**
     * 父节点查询
     * @return
     */
    @Select(value = "select * from power where parentid=0")
    List<Map> getfucha();

    /**
     * 修改
     * @param map
     * @return
     */
    @Update(value = "update power set name=#{LABEL},state=1,url=#{URL},parentid=#{PARENTID} where id=#{ID}")
    int update(Map map);
    /**
     * 添加
     * @param map
     * @return
     */
    @Insert(value = "insert into power(id,name,url,parentid,iconcls)" +
            "values(seq_power_id.nextval,#{LABEL},#{URL},#{PARENTID},'el-icon-menu')")
    int add(Map map);

    /**
     * 节点的删除
     * @param map
     * @return
     */
    @Delete(value = "delete from power where id=#{ID}")
    int delete(Map map);
}
