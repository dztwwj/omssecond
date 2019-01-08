package com.aaa.oms.dao;

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
public interface DepartureDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select * from(\n" +
            "select id,empnum,applytime,DEPARTURETIME,DEPARTURESTATUS,DEPARTUREREASON,INTENTION,OPINION,SHENHERENID,rownum rn，\n" +
            "(select ename from cu_emp where empnum = dt.empnum) ename,\n" +
            "(select dname from dept where id = (select deptid from cu_position where id = (select position from cu_emp where empnum = dt.empnum))) dname,\n" +
            "(select positionname from cu_position where id = (select position from cu_emp where empnum = dt.empnum)) position \n" +
            "from cu_departure dt  where rownum &lt; #{end} and DEPARTURESTATUS = 0 order by id) a where a.rn &gt; #{start}</script>")
    List<Map> getPageParam(Map map);
    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*)  from cu_departure where  DEPARTURESTATUS=0 </script>")
    int getPageCount(Map map);

    /**
     * 请假通过
     * @param map
     * @return
     */
    @Update(value = "update cu_leave_apply set isallow = 1,aopinion = #{AOPINIONA} where id = #{ID}")
    int updateTG(Map map);
    /**
     * 请假驳回
     * @param map
     * @return
     */
    @Update(value = "update cu_leave_apply set isallow = 2,aopinion = #{AOPINIONA} where id = #{ID}")
    int updateNoTG(Map map);

}
