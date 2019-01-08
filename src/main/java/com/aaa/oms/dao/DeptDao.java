package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:招聘的dao层
 * author:HPY
 * createTime:2018-12-11 10:24
 */
@CacheNamespace(implementation = RedisCache.class)
public interface DeptDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select id, dname,dstatus,describes,phone,(select count(e.gid) from cu_emp e where e.gid in (select gid from cu_group where deptid = a.id)) num from \n" +
                    "(select rownum rn,id,dname,dstatus,describes,phone,num from dept d \n" +
                    "where rownum &lt; #{end}  " +
                    "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>" +
                    " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from dept <where> " +
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);
    /**
     * 部门的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into dept(id,dname,describes,phone) " +
            "values(seq_dept_id.nextval,#{DNAME},#{DESCRIBES},#{PHONE})")
    int add(Map map);



    /**
     * 部门的更新
     * @param map
     * @return
     */
    @Update(value = "update dept set dname=#{DNAME},describes=#{DESCRIBES},phone=#{PHONE} where id=#{ID}")
    int update(Map map);

    /**
     * 部门的删除
     * @param id
     * @return
     */
    @Delete(value = "delete from dept where id=#{id}")
    int delete(int id);

    /**
     * 部门列表查询
     * @return
     */
    @Select(value = "select * from dept")
    List<Map> getList(Map map);


}
