package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:TrainerDao
 * discription:
 * author:zl
 * createTime:2018-12-20 08:41
 */

public interface TrainerDao {


    /**
     * 培训师查询
     * @return
     */
    @Select("<script> select a.id,a.name,a.jobnum,a.grade,a.pid,a.photo,a.email,a.phone, b.pname from cu_teacher a,cu_project b where a.pid=b.pid \n"+
            "<if test=\"name!=null and name!=''\"> and  name like '%'||#{name}||'%'</if></script>")
    List<Map> getList(Map map);

    @Insert(value="insert into cu_teacher(id,name,jobnum,grade,pid,photo,email,phone) values(seq_cu_teacher_id.nextval,#{NAME},#{JOBNUM},#{GRADE},#{PID},#{PHOTO},#{EMAIL},#{PHONE})")
    int add(Map map);

    @Update(value="update cu_teacher set name=#{NAME},jobnum=#{JOBNUM},grade=#{GRADE},pid=#{PID},photo=#{PHOTO},email=#{EMAIL},phone=#{PHONE},state=#{STATE} where id=#{ID}")
    int update(Map map);

    @Delete(value="delete from cu_teacher where id=#{id}")
    int delete(int id);

    int batchDelete(List list);

    @Select("<script> select pid,pname from CU_PROJECT</script>")
    List<Map> courseList();
}
