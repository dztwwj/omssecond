package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * className:EmployDao
 * discription:
 * author:HPY
 * createTime:2018-12-17 11:19
 */
@CacheNamespace(implementation = RedisCache.class)
public interface EmployDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select stid,stname,sex,idcard,phone,recruitdp,recruitpos,to_char(employtime,'yyyy-mm-dd') as employtime,resume,recruitnum,state,reason,id from \n" +
            "(select rownum rn,stid,stname,sex,idcard,phone,recruitdp,recruitpos,employtime,resume,recruitnum,state,reason,id from recruitstaff \n" +
            "where rownum &lt; #{end}  and  state in(${STATE}) " +
            "<if test=\"sex!=null and sex!=''\"> and sex=#{sex}</if>" +
            "<if test=\"recruitdp!=null and recruitdp!=''\"> and recruitdp like '%'||#{recruitdp}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from recruitstaff <where>  and  state in(${STATE}) " +
            "<if test=\"sex!=null and sex!=''\"> and sex=#{sex}</if>" +
            "<if test=\"recruitdp!=null and recruitdp!=''\"> and recruitdp like '%'||#{recruitdp}||'%'</if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     * 审核状态通过
     * @param map
     * @return
     */
    @Update(value = "update recruitstaff set state=#{STATE} where stid=#{STID}")
    int tgupdate(Map map);

    /**
     * 审核状态通过
     * @param map
     * @return
     */
    @Update(value = "update recruitstaff set recruitnum=recruitnum-1 where id=#{ID}")
    int tgupdate1(Map map);




    /**
     * 应聘员工信息添加
     * @param map
     * @return
     */
    @Insert(value = "insert into recruitstaff(stid,stname,sex,idcard,phone,recruitdp,recruitpos,employtime,resume,recruitnum,id) " +
            " values(seq_recruitstaff_stid.nextval,#{STNAME},#{SEX},#{IDCARD},#{PHONE},(select department from recruit where id=#{RECRUITDP}),#{RECRUITPOS},to_date(#{EMPLOYTIME},'yyyy-mm-dd'),#{RESUME},#{RECRUITNUM},#{ID})")
    int add(Map map);

    /**
     * 审核驳回理由
     * @param map
     * @return
     */
    @Update(value = "update recruitstaff set state=#{STATE},reason=#{REASON} where stid=#{STID}")
    int rsupdate(Map map);

    /**
     * 根据ID查询
     * @param map
     * @return
     */
    /*@Select(value = "select * from recruit")
    List<Map> chaxun(Map map);*/
    @Select(value = "select * from recruit where id=#{ID}")
    List<Map> chaxun(Map map);

    /*@Select(value = "select * from recruit")
    List<Map> getrecruit(Map map);*/

    /**
     * 部门列表查询
     * @return
     */
    @Select(value = "select * from recruit")
    List<Map> getdept(Map map);

    /**
     * 部门的更新
     * @param map
     * @return
     */
    //@Update(value = "update recruitstaff set stname=#{STNAME},sex=#{SEX},idcard=#{IDCARD},phone=#{PHONE},recruitdp=#{RECRUITDP},recruitpos=#{RECRUITPOS},employtime=#{EMPLOYTIME},resume=#{resume},recruitnum=#{RECRUITNUM} where stid=#{STID}")
    //int update(Map map);

    /**
     * 部门的删除
     * @param stid
     * @return
     */
    //@Delete(value = "delete from recruitstaff where stid=#{stid}")
    //int delete(int stid);

   /* @Select()
    List<Map> getjob(Map map);*/

}
