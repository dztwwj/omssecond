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
    @Select("<script>select empnum,ename,telephone,empsex,\n" +
            "empbirthday,emiladdr,idcard from\n" +
            "(select rownum rn,empnum,ename,telephone,empsex,\n" +
            "empbirthday,emiladdr,idcard\n" +
            " from cu_emp where rownum &lt; #{end}\n" +
            "<if test=\"ENAME!=null and ENAME!=''\"> and ename like '%'||#{ENAME}||'%'</if>\n" +
            ") a where a.rn &gt; #{start}</script>")
    List<Map> getPageParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from cu_emp <where> " +
            "<if test=\"ENAME!=null and ENAME!=''\"> and ENAME like '%'||#{ENAME}||'%'</if>\n" +
            "</where></script>")
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
    @Delete(value = "delete from cu_emp where EMPNUM=#{EMPNUM}")
    int delete(int id);
}
