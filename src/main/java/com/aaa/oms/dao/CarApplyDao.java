package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:CarApplyDao
 * discription:
 * author:zjf
 * createTime:2018-12-21 14:47
 */
public interface CarApplyDao {
    @Select("<script>select * from (select rownum rn, c.id cid,c.lsicense,c.cartype,\n" +
            "ca.* from cu_carinfo c,cu_carapplicatinon ca\n" +
            "where c.id=ca.carid and c.state in (${cstate}) and ca.RESULT in (${caresult})) a where \n" +
            "a.rn &gt; #{start} and a.rn &lt; #{end}" +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")

    List<Map> getCarMaintenance(Map map);

    @Select("<script>select count(*) from cu_carinfo c,cu_carapplicatinon ca\n " +
            "where c.id=ca.carid and c.state in (${cstate}) and ca.RESULT in (${caresult}) " +
            "<if test=\"EMPNAME!=null and EMPNAME!=''\"> and EMPNAME like '%'||#{EMPNAME}||'%'</if> " +
            "<if test=\"DNAME!=null and DNAME!=''\"> and DNAME like '%'||#{DNAME}||'%'</if></script> ")
    int getCarCount(Map map);

    int add(Map map);

    /**
     * 更新汽车表的状态为使用中
     * @param map
     * @return
     */
    @Update(value ="update cu_carinfo set state = 1 where id= #{CID}")
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
    @Update(value ="update cu_carinfo set state =2 where id= #{CID}")
    int turnCarToUse(Map map);
    /**
     *
     * @param map
     * @return
     */
    @Update(value ="update cu_carapplicatinon set result = 2 where id = #{ID}")
    int turnCaraToTG(Map map);

    List<Map> getCarNumber(Map map);

    int deleteCar(int id);
    int batchDelete(List list);


}
