package com.aaa.oms.dao;

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
public interface PositionDao {

    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select id,positionname,deptid,dname,rank from \n" +
            "(select rownum rn,id,positionname,deptid,(select dname from dept where id = p.deptid) dname,rank from cu_position p \n" +
            "where rownum &lt; #{end} " +
            "<if test=\"positionname!=null and positionname!=''\"> and positionname like '%'||#{positionname}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from cu_position <where>   " +
            "<if test=\"positionname!=null and positionname!=''\"> and positionname like '%'||#{positionname}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     * 职称的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_position(id,positionname,deptid,rank) " +
            "values(seq_position_id.nextval,#{POSITIONNAME},#{DEPTID},#{RANK})")
    int add(Map map);


    /**
     * 更新职称信息
     * @param map
     * @return
     */
    @Update(value = "update cu_position set positionname=#{POSITIONNAME},deptid=#{DEPTID},rank=#{RANK} where id=#{ID}")
    int update(Map map);

    /**
     * 删除职称信息
     * @param id
     * @return
     */
    @Delete(value = "delete from cu_position where id=#{ID}")
    int delete(int id);

    /**
     * 根据id查询部门名称
     * @param
     * @return
     */
    @Select(value = "select * from dept ")
    List<Map> getdname();



}
