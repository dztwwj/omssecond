package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:ResultDao
 * discription:
 * author:zl
 * createTime:2018-12-21 20:45
 */
//@Component
public interface ResultDao {


    /**
     * 考核结果查询
     * @return
     */
    @Select("<script> select a.rid,a.eid,a.empresult,a.videoresult,b.ename,b.empnum from cu_result a,cu_emp b where a.eid=b.eid  \n" +
            "<if test=\"ename!=null and ename!=''\"> and ename like '%'||#{ename}||'%'</if></script>")
    List<Map> getList(Map map);


    /**
     * 考核结果更新
     * @param map
     * @return
     */
    @Update(value = "update cu_result set empresult=#{EMPRESULT}where rid=#{RID}")
    int update(Map map);

    /**
     * 考核结果删除
     * @param rid
     * @return
     */
    @Delete(value = "delete from cu_result where rid=#{rid}")
    int delete(int rid);


    int batchDelete(List list);
}
