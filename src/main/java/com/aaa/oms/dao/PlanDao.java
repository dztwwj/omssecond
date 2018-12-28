package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PlanDao
 * discription:
 * author:zl
 * createTime:2018-12-24 09:23
 */

public interface PlanDao {

    /**
     * 计划与实施
     * @param map
     * @return
     */
    @Select("<script> select rownum rn,p.p_id,p.tid,t.name,j.pname,j.ptype,j.pdirection,e.ename,e.empnum,to_char(p.p_begin,'yyyy-mm-dd') P_BEGIN,\n" +
            "to_char(p.p_end,'yyyy-mm-dd') P_END from cu_plan p left join cu_teacher t \n" +
            "on p.tid=t.id left join cu_project j on t.pid=j.pid left join cu_emp e on p.batch=e.eid \n" +
            "<where>\n" +
            "prstate=0\n" +
            "<if test=\"pname!=null and pname!=''\">\n" +
            "and j.pname like '%'||#{pname}||'%'\n" +
            "</if>\n" +
            "<if test=\"empnum!=null and empnum!=''\">\n" +
            "and e.empnum like '%'||#{empnum}||'%'\n" +
            "</if>\n"+
            "</where></script>")
    List<Map> getList(Map map);

    /**
     *
     * @param map
     * @return
             */
    @Insert(value = " insert into cu_plan values(seq_cu_plan_id.nextval,#{tid},#{pid},to_date(substr(#{P_BEGIN},1,10),'yyyy-MM-dd'),to_date(substr(#{P_END},1,10),'yyyy-MM-dd'),#{BATCH},0)")
    int add(Map map);

    /**
     *
     * @param map
     * @return
     */
    @Update(value = " update cu_plan set TID=#{TID},NAME=#{pid},P_BEGIN=to_date(#{P_BEGIN},'yyyy-MM-dd'),\n" +
            "P_END=to_date(#{P_END},'yyyy-MM-dd'),BATCH=#{BATCH} where P_ID=#{P_ID}")
    int update(Map map);

    /**
     *
     * @param p_id
     * @return
     */
    @Delete(value = "delete from cu_plan where p_id=#{p_id}")
    int delete(int p_id);

    @Select("<script>select pid,pname from cu_project </script>")
    List<Map> courseList1();

    int batchDelete(List list);

    @Select("<script> select id,name from cu_teacher where pid=#{pid}</script>")
    List<Map> courseList(int pid);

    @Select("<script> select eid,ename from cu_emp</script>")
    List<Map> planList();

}
