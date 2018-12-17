package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;


/**
 * className:RecruitDao
 * discription:
 * author:HPY
 * createTime:2018-12-15 10:52
 */
@CacheNamespace(implementation = RedisCache.class)
public interface RecruitDao {


    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select id,department,recruitnum,to_char(releasetime,'yyyy-mm-dd') as releasetime,education,workexer,jobs,language,jobrespon,empid,empname from \n" +
            "(select rownum rn,id,department,recruitnum,releasetime,education,workexer,jobs,language,jobrespon,empid,empname from recruit \n" +
            "where rownum &lt; #{end}  " +
            "<if test=\"department!=null and department!=''\"> and department like '%'||#{department}||'%'</if>" +
            "<if test=\"releasetime!=null and releasetime!=''\"> and releasetime like '%'||#{releasetime}||'%'</if> " +
            ")a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from recruit <where> " +
            "<if test=\"department!=null and department!=''\"> and department like '%'||#{department}||'%'</if>" +
            "<if test=\"releasetime!=null and releasetime!=''\"> and releasetime like '%'||#{releasetime}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     *
     * @param map
     * @return
     */
    @Insert(value = "insert into recruit values(sql_recruit_id.nextval,#{DEPARTMENT},#{RECRUITNUM},to_date(#{RELEASETIME},'yyyy-mm-dd'),#{EDUCATION}," +
            "#{WORKEXER},#{JOBS},#{LANGUAGE},#{JOBRESPON},#{EMPID},#{EMPNAME})")
    int add(Map map);

    /**
     * 更新招聘信息
     * @param map
     * @return
     */
    @Update(value = "update recruit set department=#{DEPARTMENT},recruitnum=#{RECRUITNUM},releasetime=to_date(#{RELEASETIME},'yyyy-mm-dd'),education=#{EDUCATION}," +
            "workexer=#{WORKEXER},jobs=#{JOBS},language=#{LANGUAGE},jobrespon=#{JOBRESPON} where id=#{ID}")
    int update(Map map);

    /**
     * 删除招聘信息
     * @param id
     * @return
     */
    @Delete(value = "delete from recruit where id=#{id}")
    int delete(int id);
}
