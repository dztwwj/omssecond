package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:CarReturnDao
 * discription:
 * author:zjf
 * createTime:2018-12-27 12:06
 */
public interface CarReturnDao {
    /**
     *还车车辆查询
     * @param map
     * @return
     */
    @Select("<script>" +
            "select * from (select  rownum rn,c.id,c.dname ,c.empno,c.empname,to_char(c.applytime,'yyyy-mm-dd') applytime,\n" +
            "c.startplace,c.endplace,\n" +
            "c.carid,\n" +
            "(select ci.lsicense from cu_carinfo ci where c.carid = ci.id) lsicense,\n" +
            "c.peoplenumber,c.TELL,c.REASONS,\n" +
            "c.result from cu_carapplicatinon c,cu_carinfo ci "+
            "where ci.id=c.carid and ci.state in (${cstate}) and c.RESULT in (${caresult})) a where " +
            "a.rn &gt; #{start} and a.rn &lt; #{end}" +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")
    List<Map> getCarMaintenance(Map map);

    /**
     * 后台车辆审核查询总数
     * @param map
     * @return
     */
    @Select("<script>" +
            "select count(*) from cu_carinfo c,cu_carapplicatinon ca\n " +
            "where c.id=ca.carid and c.state in (${cstate}) and ca.RESULT in (${caresult}) " +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")
    int getCarCount(Map map);
    /**
     *更改车辆状态为可使用
     * @param map
     * @return
     */
    @Update(value ="update cu_carinfo set state =1 where id= #{CARID}")
    int turnCarToUse(Map map);
    /**
     *车辆申请信息状态为还车成功
     * @param map
     * @return
     */
    @Update(value ="update cu_carapplicatinon set result = 3,endtime=sysdate where id = #{ID}")
    int turnCaraToTG(Map map);
}
