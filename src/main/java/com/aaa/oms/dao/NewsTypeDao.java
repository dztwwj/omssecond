package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:NewsTypeDao
 * discription:dao层
 * author:HPY
 * createTime:2018-12-11 10:24
 */
@CacheNamespace(implementation = RedisCache.class)
public interface NewsTypeDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select ncid,ncname,describe from \n" +
            "(select rownum rn,ncid,ncname,describe from CU_NEWS_CLASS\n" +
            "where rownum &lt; #{end}  " +
            "<if test=\"ncname!=null and ncname!=''\"> and ncname like '%'||#{ncname}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from CU_NEWS_CLASS <where> " +
            "<if test=\"ncname!=null and ncname!=''\"> and ncname like '%'||#{ncname}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);
    /**
     * 附件的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into CU_NEWS_CLASS values(seq_CU_NEWS_CLASS_ncid.nextval,#{NCNAME},#{DESCRIBE})")
    int add(Map map);



    /**
     * 附件的更新
     * @param map
     * @return
     */
    @Update(value = "update CU_NEWS_CLASS set ncname=#{NCNAME},describe=#{DESCRIBE} where ncid=#{NCID}")
    int update(Map map);

    /**
     * 附件的删除
     * @param ncid
     * @return
     */
    @Delete(value = "delete from CU_NEWS_CLASS where ncid=#{ncid}")
    int delete(int ncid);
}
