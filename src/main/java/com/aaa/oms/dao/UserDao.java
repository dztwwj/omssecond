package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import sun.java2d.pipe.PixelDrawPipe;

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
            "<if test=\"ISEFFECTIVE!=null and ISEFFECTIVE!=''\"> and ISEFFECTIVE = #{ISEFFECTIVE} </if> " +
            "<if test=\"empnumQian!=null and empnumQian!=''\"> and empnum = #{empnumQian} </if> " +
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
            "<if test=\"ISEFFECTIVE!=null and ISEFFECTIVE!=''\"> and ISEFFECTIVE = #{ISEFFECTIVE}</if> " +
            "<if test=\"empnumQian!=null and empnumQian!=''\"> and empnum = #{empnumQian} </if> " +
            " </where>" +
            "</script>")
    int getPageCount(Map map);
    /**
     * 员工的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_emp(addempnum,empnum,eid,ename,telephone,empsex,empbirthday,emiladdr,idcard,position,gid,INDUCTIONTIME) values(#{addEmpnumm},seq_empnum.nextval,seq_emp_id.nextval,#{ENAME},#{TELEPHONE},#{EMPSEX},to_date((substr(#{IDCARD},7,8)),'yyyy-mm-dd'),#{EMILADDR},#{IDCARD},#{PNAME},#{GNAME},sysdate)")
    int add(Map map);



    /**
     * 员工的更新
     * @param map
     * @return
     */
    @Update(value = "update cu_emp set ename=#{ENAME},telephone=#{TELEPHONE},emiladdr=#{EMILADDR},idcard = #{IDCARD},position = #{POSITION},gid = #{GID} where empnum=#{EMPNUM}")
    int update(Map map);
    /**
     * 员工自己修改信息
     * @param map
     * @return
     */
    @Update(value = "update cu_emp set ename=#{ENAME},telephone=#{TELEPHONE},emiladdr=#{EMILADDR},idcard = #{IDCARD} where empnum=#{EMPNUM}")
    int updateQian(Map map);

    /**
     * 员工的删除
     * @param id
     * @return
     */
    @Delete(value = "update cu_emp set ISEFFECTIVE = 1 where EMPNUM=#{EMPNUM}")
    int delete(int id);

    /**
     * 根据部门id查询当前班组
     * @param id
     * @return
     */
    @Select(value = "select * from cu_group where deptid=( select deptid from cu_position where id = #{id})")
    List<Map> selectG(Integer id);

    /**
     * 根据empnum查询员工信息
     * @param map
     * @return
     */
    @Select(value = "select * from cu_emp where empnum=#{dempnum}")
    List<Map> selectEvery(Map map);

    /**
     * 根据session的职位id查询职级
     * @param pid
     * @return
     */
    @Select(value = "select rank from cu_position where id = #{pid}")
    int selectRank(Object pid);
}
