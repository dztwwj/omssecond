package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:CarinfoDao
 * discription:
 * author:zjf
 * createTime:2018-12-12 15:22
 */
public interface CarinfoDao {
    /**
     * 带分页的显示汽车方法
     *
     */
    @Select("<script>select a.ID,a.LSICENSE,a.CARTYPE,a.SADDLE,a.APPLICATIONTYPE,a.DRIVER,to_char(a.BEGINTIME,'yyyy-mm-dd') as BEGINTIME,a.STATE,to_char(a.INSURANCETIMR,'yyyy-mm-dd') as INSURANCETIMR,a.MILEAGE,a.REMARK from  \n" +
            "(select rownum rn,b.* from CU_CARINFO b\n" +
            "where rownum &lt; #{end}   " +
            "<if test=\"LSICENSE!=null and LSICENSE!=''\"> and LSICENSE=#{LSICENSE}</if>" +
            "<if test=\"CARTYPE!=null and CARTYPE!=''\"> and CARTYPE like '%'||#{CARTYPE}||'%'</if>" +
            "<if test=\"SADDLE!=null and SADDLE!=''\">  and SADDLE =#{SADDLE}</if>" + " )" +
            "a where a.rn &gt; #{start} </script>")
    List<Map> getCarInfoDao(Map map);
    /**
     *显示汽车总数量
     */
    @Select("<script> select count(*) from CU_CARINFO <where>" +
            "<if test=\"LSICENSE!=null and LSICENSE!=''\"> and LSICENSE=#{LSICENSE}</if>" +
            "<if test=\"CARTYPE!=null and CARTYPE!=''\"> and CARTYPE like '%'||#{CARTYPE}||'%'</if>" +
            "<if test=\"SADDLE!=null and SADDLE!=''\">  and SADDLE =#{SADDLE}</if>" +
            " </where></script>")
    int getCarCount(Map map);

    /**to_date(#{hiredate},'yyyy-mm-dd'
     * 汽车的添加方法#{INSURANCETIMR}
     * #{BEGINTIME}
     */
    @Insert("insert into CU_CARINFO values(seq_CU_CARINFO_id.nextval,#{LSICENSE},#{CARTYPE},#{SADDLE},#{APPLICATIONTYPE},#{DRIVER},to_date(#{BEGINTIME},'yyyy-mm-dd'),#{STATE},to_date(#{INSURANCETIMR},'yyyy-mm-dd'),#{MILEAGE},#{REMARK})")
    int addCar(Map map);
    /**
     * 汽车更新方法
     * ("update tb_user set ENAME=#{ENAME},JOB=#{JOB},HIREDATE=to_date(substr(#{HIREDATE},1,10),'yyyy-mm-dd'),SAL=#{SAL},COMM=#{COMM},DEPTNO=#{DEPTNO} where empno=#{EMPNO}")
     */
    @Insert("update CU_CARINFO set LSICENSE=#{LSICENSE},CARTYPE=#{CARTYPE},SADDLE=#{SADDLE},APPLICATIONTYPE=#{APPLICATIONTYPE},DRIVER=#{DRIVER},BEGINTIME=to_date(substr(#{BEGINTIME},1,10),'yyyy-mm-dd'),STATE=#{STATE},INSURANCETIMR=to_date(substr(#{INSURANCETIMR},1,10),'yyyy-mm-dd'),MILEAGE=#{MILEAGE},REMARK=#{REMARK} where id=#{ID}")

    int updateCar(Map map);
    /**
     * 汽车删除方法
     */
    @Insert("delete from CU_CARINFO where id=#{ID}")
    int deleteCar(int id);
    /**
     * 批量删除
     */
    @Delete("<script>delete from CU_CARINFO where id in" +
            "<foreach collection='list' item='en' open='(' close=')' separator=','>#{en}</foreach></script>")
    int batchDelete(List list);

}
