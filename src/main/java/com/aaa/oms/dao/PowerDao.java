package com.aaa.oms.dao;

import com.aaa.oms.entity.Node;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PowerDao
 * discription:
 * author:zz00000
 * createTime:2018-12-11 09:22
 */
@Component
public interface PowerDao {

    /**
     * 权限列表查询
     * @return
     */
    @Select("select id, name as label, iconcls as iconClass, url, parentid pid from power")
    List<Node> getList();

    /**
     * 根据j角色Id找树
     * @param map
     * @return
     */
    @Select("select id, name as label, iconcls as iconClass, url, parentid pid from power " +
            "where id in(select powerid from cu_role_power where posid = #{roleid})")
    List<Node> getChecktree(Map map);

    /**
     * 删除原有权限
     * @param map
     * @return
     */
    @Delete("delete from cu_role_power where posid=#{ID}")
    int delPower(Map map);

    /**
     * 添加权限
     * @param map
     * @return
     */
    @Insert("insert into cu_role_power(id,posid,powerid) values(seq_cu_role_power_id.nextval,#{ID},#{powerid})")
    int savePower(Map map);
}
