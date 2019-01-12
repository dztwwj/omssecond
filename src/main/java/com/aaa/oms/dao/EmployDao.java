package com.aaa.oms.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * className:EmployDao
 * discription:
 * author:HPY
 * createTime:2018-12-17 11:19
 */
//@CacheNamespace(implementation = RedisCache.class)
public interface EmployDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select stid,stname,sex,idcard,phone,recruitdp,recruitpos,to_char(employtime,'yyyy-mm-dd') as employtime,resume,recruitnum,state,reason,id,email from \n" +
            "(select rownum rn,stid,stname,sex,idcard,phone,recruitdp,recruitpos,employtime,resume,(select numbers from recruit where id=61) recruitnum,state,reason,id,email from recruitstaff \n" +
            "where rownum &lt; #{end}  and  state in (${STATE})   and phone not in(select a.telephone from cu_emp a)  " +
            "<if test=\"sex!=null and sex!=''\"> and sex=#{sex} </if> " +
            "<if test=\"recruitdp!=null and recruitdp!=''\"> and recruitdp like '%'||#{recruitdp}||'%'</if>" +
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from recruitstaff <where> state in (${STATE})    and phone not in(select a.telephone from cu_emp a) " +
            "<if test=\"sex!=null and sex!=''\"> and sex=#{sex}</if>" +
            "<if test=\"recruitdp!=null and recruitdp!=''\"> and recruitdp like '%'||#{recruitdp}||'%'</if>" +
            " </where> </script>")
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
    @Update(value = "update recruit set numbers=numbers-1 where id=#{ID}")
    int tgupdate1(Map map);


    /**
     * 入职新员工完善信息
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_emp(eid,empnum,ename,telephone,empsex,gid,emiladdr,idcard,position,inductiontime) " +
            "values(seq_cu_emp_id.nextval,seq_empnum.nextval,#{STNAME},#{PHONE},#{SEX},#{GID},#{EMAIL},#{IDCARD},#{POSITION},SYSDATE)")
    int perfectadd(Map map);



    /**
     * 应聘员工信息添加
     * @param map
     * @return
     */
    @Insert(value = "insert into recruitstaff(stid,stname,sex,idcard,phone,recruitdp,recruitpos,employtime,recruitnum,id,email,resume) " +
            " values(seq_recruitstaff_stid.nextval,#{STNAME},#{SEX},#{IDCARD},#{PHONE},#{DEPARTMENT},#{JOBS},SYSDATE,#{NUMBERS},#{ID},#{EMAIL},#{RESUME})")
    int addEmploy(Map map);


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
    @Select(value = "select numbers from recruit where id=#{ID}")
    List<Map> chaxun(Map map);


    /**
     * 部门列表查询
     * @return
     */
    @Select(value = "select * from recruit")
    List<Map> getdept(Map map);

    /**
     * 班组查询
     * @return
     */
    @Select(value = "select * from cu_group ")
    List<Map> getgid();

    /**
     * 查询职称
     * @return
     */
    @Select(value = "select * from cu_position")
    List<Map> getposid();
}
