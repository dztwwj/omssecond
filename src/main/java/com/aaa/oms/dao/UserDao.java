package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:LiuQian
 * createTime:2018-12-14 21:10:16
 */
public interface UserDao {

    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select * from (\n" +
            "select eid,rownum rn,empnum,ename,ISEFFECTIVE,ADDEMPNUM,TELEPHONE,EMPSEX,to_char(empbirthday,'yyyy-mm-dd') empbirthday,ISLOCK,LOCKTIME,GID,EMILADDR,IDCARD,EMPFACE,LOGINNUM,POSITION,\n" +
            "DEPARTURETIME,LEAVINGREASON,to_char(INDUCTIONTIME,'yyyy-mm-dd') INDUCTIONTIME,BATCH,PERMS,DEPTID,to_char(to_date((substr(idcard,7,8)),'yyyy-mm-dd'),'yyyy-mm-dd') birthday ,\n" +
            "(select dname from dept where id = (select deptid from cu_position p where id = e.position)) dname,\n" +
            "(select positionname from cu_position where id =e.position) pname,\n" +
            "(select gname from cu_group where gid = e.gid) gname, \n" +
            "(select ename from cu_emp em where em.empnum = e.addempnum) addname \n" +
            "from cu_emp e where  rownum &lt; #{end}" +
            "<if test=\"ENAME!=null and ENAME!=''\"> and ename like '%'||#{ENAME}||'%'</if>" +
            "<if test=\"EMPNUM!=null and EMPNUM!=''\"> and EMPNUM = #{EMPNUM} </if>" +
            "<if test=\"ISEFFECTIVE!=null and ISEFFECTIVE!=''\"> and ISEFFECTIVE = #{ISEFFECTIVE}</if>" +
            " order by eid\n" +
            ") a where a.rn  &gt; #{start}</script>")
//    <if test="ENAME!=null and ENAME!=''"> and ename like '%'||#{ENAME}||'%'</if>
    List<Map> getPageParam(Map map);
    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from cu_emp <where>" +
            "<if test=\"ENAME!=null and ENAME!=''\"> and ename like '%'||#{ENAME}||'%'</if>" +
            "<if test=\"EMPNUM!=null and EMPNUM!=''\"> and EMPNUM = #{EMPNUM} </if>" +
            "<if test=\"ISEFFECTIVE!=null and ISEFFECTIVE!=''\"> and ISEFFECTIVE = #{ISEFFECTIVE}</if> </where>" +
            "</script>")
    int getPageCount(Map map);
    /**
     * 员工的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_emp(empnum,eid,ename,telephone,empsex,empbirthday,emiladdr,idcard) values(seq_empnum.nextval,seq_emp_id.nextval,#{ENAME},#{TELEPHONE},#{EMPSEX},sysdate,#{EMILADDR},#{IDCARD})")
    int add(Map map);



    /**
     * 员工的更新
     * @param map
     * @return
     */
    @Update(value = "update cu_emp set ename=#{ENAME},telephone=#{TELEPHONE},empsex=#{EMPSEX},emiladdr=#{EMILADDR}，idcard=#{IDCARD} where empnum=#{EMPNUM}")
    int update(Map map);

    /**
     * 员工的删除
     * @param id
     * @return
     */
    @Delete(value = "update cu_emp set ISEFFECTIVE = 1 where EMPNUM=#{EMPNUM}")
    int delete(int id);
}
