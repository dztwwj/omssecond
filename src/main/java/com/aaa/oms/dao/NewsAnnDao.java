package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:NewsAnnDao
 * discription:dao层
 * author:HPY
 * createTime:2018-12-11 10:24
 */
//@CacheNamespace(implementation = RedisCache.class)
public interface NewsAnnDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select naid,filepath,filesize,nid,filename,isdelete from \n" +
            "(select rownum rn,naid,filepath,filesize,nid,filename,isdelete from CU_NEWS_ANNEXES\n" +
            "where rownum &lt; #{end}  " +
            "<if test=\"filename!=null and filename!=''\"> and filename like '%'||#{filename}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from CU_NEWS_ANNEXES <where> " +
            "<if test=\"filename!=null and filename!=''\"> and filename like '%'||#{filename}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);
    /**
     * 附件的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into CU_NEWS_ANNEXES values(seq_CU_NEWS_ANNEXES_naid.nextval,#{FILEPATH},#{FILESIZE},#{NID},#{FILENAME},#{ISDELETE})")
    int add(Map map);

    /**
     * 附件的更新
     * @param map
     * @return
     */
    @Update(value = "update CU_NEWS_ANNEXES set filepath=#{FILEPATH},filesize=#{FILESIZE},nid=#{NID},filename=#{FILENAME},isdelete=#{ISDELETE} where naid=#{NAID}")
    int update(Map map);

    /**
     * 附件的删除
     * @param naid
     * @return
     */
    @Delete(value = "delete from CU_NEWS_ANNEXES where naid=#{naid}")
    int delete(int naid);
}
