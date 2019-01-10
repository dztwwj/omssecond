package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:CarApplyDao
 * discription:车辆申请
 * author:zjf
 * createTime:2018-12-21 14:47
 */
public interface CarApplyDao {
    /**
     * 后台车辆审核查询
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
            "where ci.id=c.carid  and c.RESULT in (${caresult})) a where " +
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
            "where c.id=ca.carid and  ca.RESULT in (${caresult}) " +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")
    int getCarCount(Map map);

    /**
     * 前台车辆审核查询
     * @param map
     * @return
     */
    @Select("<script>" +
            "select * from (select  rownum rn,c.id,c.dname ,c.empno,c.empname,to_char(c.applytime,'yyyy-mm-dd') applytime,\n" +
            "c.startplace,c.endplace,\n" +
            "c.carid,\n" +
            "(select ci.lsicense from cu_carinfo ci where c.carid = ci.id) lsicense,\n" +
            "c.peoplenumber,c.TELL,c.REASONS,\n" +
            "c.result from cu_carapplicatinon c where empno not in (0)) a where " +
            "a.rn &gt; #{start} and a.rn &lt; #{end}" +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")

    List<Map> getCarMaintenanceQian(Map map);

    /**
     * 前台车辆审核查询总数
     * @param map
     * @return
     */
    @Select("<script>" +
            "select count(*) from cu_carapplicatinon ca " +
            "where empno not in (0) " +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")
    int getCarCountQian(Map map);

    @Insert("insert into cu_carapplicatinon(id,dname,empno,empname,applytime,startplace,endplace,carid,peoplenumber,tell,reasons,result) values(" +
            "seq_CU_CARINFO_id.nextval,'开发部','55','刘谦'," +
            "sysdate," +
            "#{STARTPLACE},#{ENDPLACE}," +
            "#{ID},#{PEOPLENUMBER},#{TELL},#{REASONS},'0')")
    int add(Map map);

    /**
     * 更新汽车表的状态为使用中
     * @param map
     * @return
     */
    @Update(value ="update cu_carinfo set state = 3 where id= #{CARID}")
    int updateCarToUse(Map map);
    /**
     * 更新汽车申请表的状态为审核通过
     * @param map
     * @return
     */
    @Update(value ="update cu_carapplicatinon set result = 1 where id = #{ID}")
    int updateCaraToTG(Map map);
    /**
     *
     * @param map
     * @return
     */
    @Update(value ="update cu_carinfo set state =2 where id= #{CARID}")
    int turnCarToUse(Map map);
    /**
     *
     * @param map
     * @return
     */
    @Update(value ="update cu_carapplicatinon set result = 2 where id = #{ID}")
    int turnCaraToTG(Map map);

//    /**
//     * 驳回
//     * @param id
//     * @return
//     */
//    int deleteCar(int id);


    /**
     * 查询车辆的车牌号
     *
     * @return
     */
    @Select(value = "select id, lsicense, cartype,saddle,applicationtype,driver,state,insurancetimr,mileage,remark from cu_carinfo where state =1 ")
    List<Map> getLiscense();

}
