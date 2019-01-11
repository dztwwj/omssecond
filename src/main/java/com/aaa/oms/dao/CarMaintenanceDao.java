package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:CarMaintenanceDao
 * discription:
 * author:zjf
 * createTime:2018-12-18 09:02
 */
public interface CarMaintenanceDao {
    /**
     * 显示数据库的的车辆维修表,分页显示
     *
     */
    @Select("<script>select ID, LICENSE,CARTYPE,FACTORY,COSTS,CONTENTD,CARTIME from \n" +
            "(select rownum rn,ID,LICENSE,CARTYPE,FACTORY,COSTS,CONTENTD,CARTIME from CU_CARMAINTENANCE\n" +
            "where rownum &lt; #{end}  " +
            "<if test=\"LICENSE!=null and LICENSE!=''\"> and LICENSE like '%'||#{LICENSE}||'%'</if>" +
            "<if test=\"CARTYPE!=null and CARTYPE!=''\"> and CARTYPE like '%'||#{CARTYPE}||'%'</if>"+
            " )a where a.rn &gt; #{start} </script>")
    List<Map> getCarMaintenance(Map map);

    /**
     * 获取维修车辆总数量
     * select count(*) from cu_carmaintenance
     */
    @Select("<script> select count(*) from CU_CARMAINTENANCE <where> " +
            "<if test=\"LICENSE!=null and LICENSE!=''\"> and LICENSE like '%'||#{LICENSE}||'%'</if>" +
            " </where></script>")
    int getCarCount(Map map);

    /**
     * 添加维修的车辆
     */
    @Insert("insert into CU_CARMAINTENANCE values(seq_CU_CARMAINTENANCE_id.nextval,#{LICENSE},#{CARTYPE},#{FACTORY},#{COSTS},#{CONTENTD},#{CARTIME})")

    int add(Map map);
    /**
     * 修改维修的车辆,有可能输入错了
     */
    @Update("update CU_CARMAINTENANCE set LICENSE=#{LICENSE},CARTYPE=#{CARTYPE},FACTORY=#{FACTORY},COSTS=#{COSTS},CONTENTD=#{CONTENTD},CARTIME=#{CARTIME} where id=#{ID}")

    int update(Map map);
    /**
     * 动态获取本公司有的车辆车牌号和车型
     */
    List<Map> getCarNumber(Map map);

    /**
     * 维修汽车删除方法
     */
    @Insert("delete from CU_CARMAINTENANCE where id=#{ID}")
    int deleteCar(int id);
    /**
     * 维修批量删除
     */
    @Delete("<script>delete from CU_CARMAINTENANCE where id in" +
            "<foreach collection='list' item='en' open='(' close=')' separator=','>#{en}</foreach></script>")
    int batchDelete(List list);

}
