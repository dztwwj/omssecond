package com.aaa.oms.dao;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:CourseDao
 * discription:课程管理Dao层
 * author:zl
 * createTime:2018-12-17 11:53
 */
@Component
public interface CourseDao {
    /**
     * 课程查询
     * @return
     */
    @Select("<script> select PID,PNAME,PTYPE,PDIRECTION,PSTATE from CU_PROJECT  \n" +
            "<where><if test=\"pname!=null and pname!=''\"> pname like '%'||#{pname}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 课程的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into CU_PROJECT values(seq_dept_id.nextval,#{PNAME},#{PTYPE},#{PDIRECTION},#{PSTATE})")
    int add(Map map);

    /**
     * 课程的更新
     * @param map
     * @return
     */
    @Update(value = "update CU_PROJECT set pname=#{PNAME},pdirection=#{PDIRECTION},pstate=#{PSTATE},ptype=#{PTYPE} where pid=#{PID}")
    int update(Map map);

    /**
     * 课程的删除
     * @param pid
     * @return
     */
    @Delete(value = "delete from CU_PROJECT where pid=#{pid}")
    int delete(int pid);


    int batchDelete(List list);
}
