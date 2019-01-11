package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:NewsDao
 * discription:dao层
 * author:
 * createTime:2018-12-11 10:24
 */
@CacheNamespace(implementation = RedisCache.class)
public interface NewsDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select nid,ntitle,nintro,nclob,addempname,sectionname,top,confirmid,isdelete,titlebold,titlecolor,coverplotpath,to_char(addtime,'yyyy/mm/dd hh24:mi:ss')  addtime from \n" +
            "(select rownum rn,nid,ntitle,nclob,nintro,addempname,sectionname,top,confirmid,isdelete,titlebold,titlecolor,coverplotpath,addtime from CU_NEWS\n" +
            "where rownum &lt; #{end}  " +
            "<if test=\"ntitle!=null and ntitle!=''\"> and ntitle like '%'||#{ntitle}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 前台新闻查询
     * @param
     * @return
     */
    @Select("select to_date(#{ADDTIME},'yyyy-mm-dd hh24:mi:ss'),ntitle from CU_NEWS where nid ")
    List<Map> getnews();

    /**
     * 根据nid更新删除状态
     * @param NID
     * @return
     */
    @Update("update CU_NEWS set isdelete=2 where nid=#{NID}")
    int updateNews(int NID);

    /**
     * 根据nid更新发布状态
     * @param NID
     * @return
     */
    @Update("update CU_NEWS set confirmid=2 where nid=#{NID}")
    int updateCon(int NID);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from CU_NEWS <where> " +
            "<if test=\"ntitle!=null and ntitle!=''\"> and ntitle like '%'||#{ntitle}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);
    /**
     * 新闻的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into CU_NEWS(nid,ntitle,nintro,addempname,sectionname,top,titlebold,titlecolor,coverplotpath,addtime) values(seq_CU_NEWS_nid.nextval,#{NTITLE},#{NINTRO},#{ADDEMPNAME},#{SECTIONNAME},#{TOP},#{TITLEBOLD},#{TITLECOLOR},#{COVERPLOTPATH},to_date(#{ADDTIME},'yyyy-mm-dd hh24:mi:ss'))")
    int add(Map map);

    /**
     * 新闻的更新
     * @param map
     * @return
     */
    @Update(value = "update CU_NEWS set ntitle=#{NTITLE},nintro=#{NINTRO},addempname=#{ADDEMPNAME},sectionname=#{SECTIONNAME},top=#{TOP},confirmid=#{CONFIRMID},isdelete=#{ISDELETE},titlebold=#{TITLEBOLD},titlecolor=#{TITLECOLOR},coverplotpath=#{COVERPLOTPATH},addtime=to_date(#{ADDTIME},'yyyy-mm-dd hh24:mi:ss') where nid=#{NID}")
    int update(Map map);


}
