package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PromoteDao
 * discription:晋升信息的Dao层
 * author:LiuQian
 * createTime:2018-12-17 21:25:31
 */
public interface LeaveDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select * from \n" +
            "(select rownum rn,to_char(BEGINTIME,'yyyy-mm-dd') BEGINTIME,to_char(ENDTIME,'yyyy-mm-dd') ENDTIME,LEAVETYPEID,ISALLOW,LEAVECAUSE,ID,EMPNUMB,LEAVEDAYSUM,to_char(APPLYTIME,'yyyy-mm-dd') APPLYTIME,auditornum,aopinion, \n" +
            "(select leavetype from cu_leave_type where typeid =la.leavetypeid) leavetype ,\n" +
            "(select ename from cu_emp where empnum=la.empnumb ) ename,\n" +
            "(select ename from cu_emp where empnum = la.auditornum) auname ,\n" +
            "(select positionname from cu_position where id = (select position from cu_emp where empnum = la.empnumb)) positionname,\n" +
            "(select dname from dept where id = (select deptid from cu_position where id = (select position from cu_emp where empnum = la.empnumb))) dname,"+
            "(select deptid from cu_position where id = (select position from cu_emp where empnum = la.empnumb)) deptid,\n"+
            "(select positionname from cu_position where id = (select position from cu_emp where empnum = la.auditornum)) aupositionname \n" +
            "from cu_leave_apply la where la.isallow in (${isallow}) and rownum &lt; #{end} <if test=\"STATE!=null and STATE!=''\"> and la.ISALLOW = #{STATE}</if> " +
            "<if test=\"gid!=null and gid!=''\"> and gid = #{gid}</if>" +
            "<if test=\"grade!=null and grade!=''\"> and grade = #{grade}</if>" +
            "<if test=\"EMPNUMQ!=null and EMPNUMQ!=''\"> and la.EMPNUMB = #{EMPNUMQ}</if>) a " +
            "where a.rn &gt; #{start}</script>")
    List<Map> getPageParam(Map map);
    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*)  from cu_leave_apply la where la.isallow in (${isallow}) <if test=\"STATE!=null and STATE!=''\"> and ISALLOW = #{STATE}</if> <if test=\"EMPNUMQ!=null and EMPNUMQ!=''\"> and la.EMPNUMB = #{EMPNUMQ}</if>" +
            "<if test=\"gid!=null and gid!=''\"> and gid = #{gid}</if>" +
            "<if test=\"grade!=null and grade!=''\"> and grade = #{grade}</if>" +
            " </script>")
    int getPageCount(Map map);

    /**
     * 请假通过
     * @param map
     * @return
     */
    @Update(value = "update cu_leave_apply set isallow = 1,aopinion = #{AOPINIONA} where id = ${ID}")
    int updateTG(Map map);
    /**
     * 请假通过
     * @param map
     * @return
     */
    @Update(value = "update cu_leave_apply set grade=2 where id = ${ID}")
    int updateTGTo2(Map map);


    /**
     * 请假驳回
     * @param map
     * @return
     */
    @Update(value = "update cu_leave_apply set isallow = 2,aopinion = #{AOPINIONA} where id = #{ID}")
    int updateNoTG(Map map);

    /**
     * 请假添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_leave_apply(EMPNUMB,ID,BEGINTIME,ENDTIME,LEAVETYPEID,LEAVECAUSE,APPLYTIME,LEAVEDAYSUM,grade,gid) " +
            "values(#{EMPNUM},seq_cu_leave.nextval,to_date(substr(#{BEGINTIME},1,10),'yyyy/mm/dd'),to_date(substr(#{BEGINTIME},1,10),'yyyy/mm/dd')+#{LEAVEDAYSUM},#{LEAVETYPEID},#{LEAVECAUSE},sysdate,#{LEAVEDAYSUM},#{grade},#{gid})")
    int add(Map map);

}
