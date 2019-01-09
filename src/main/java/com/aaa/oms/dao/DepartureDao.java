package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PromoteDao
 * discription:离职信息的Dao层
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
            "select id,advice,empnum,applytime,DEPARTURETIME,DEPARTURESTATUS,DEPARTUREREASON,INTENTION,OPINION,SHENHERENID,rownum rn，\n" +
            "(select ename from cu_emp where empnum = dt.empnum) ename,\n" +
            "(select dname from dept where id = (select deptid from cu_position where id = (select position from cu_emp where empnum = dt.empnum))) dname,\n" +
            "(select positionname from cu_position where id = (select position from cu_emp where empnum = dt.empnum)) position \n" +
            "from cu_departure dt  where rownum &lt; #{end} and DEPARTURESTATUS in (${status}) " +
            "<if test=\"STATE!=null and STATE!=''\"> and DEPARTURESTATUS = #{STATE}</if>" +
            " order by id) a where a.rn &gt; #{start}</script>")
    List<Map> getPageParam(Map map);
    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*)  from cu_departure where  DEPARTURESTATUS in (${status}) <if test=\"STATE!=null and STATE!=''\"> and DEPARTURESTATUS = #{STATE}</if> </script>")
    int getPageCount(Map map);

    /**
     * 离职通过 改变数据库状态
     * @param map
     * @return
     */
    @Update(value = "update cu_departure set departurestatus = 2 where id = ${ID}")
    int updateTG(Map map);

    /**
     * 离职审核，添加的建议
     * @param map
     * @return
     */
    @Update(value = "update cu_departure set advice = '${ADVICE}' where id = ${ID}")
    int updatee(Map map);

    /**
     * 离职成功，修改员工表的在职状态
     * @param map
     * @return
     */
    @Update(value = "update cu_emp set ISEFFECTIVE = 1 where empnum = ${EMPNUM}")
    int updateEmp(Map map);
    /**
     * 离职驳回，改变数据库状态
     * @param map
     * @return
     */
    @Update(value = "update cu_departure set departurestatus = 1 where id = ${ID}")
    int updateNoTG(Map map);

}
