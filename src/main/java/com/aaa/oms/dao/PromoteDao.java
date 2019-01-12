package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PromoteDao
 * discription:晋升信息的Dao层
 * author:LiuQian
 * createTime:2018-12-17 21:25:31
 */
public interface PromoteDao{
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select * from" +
            "(select rownum rn,b.* from" +
                "(select " +
                    "r.id,r.positionid," +
                    "(select positionname from cu_position where id=r.positionid) positionname," +
                    "r.demandnum," + //所需人数
                    "to_char(r.endapply,'yyyy-mm-dd') endapply," +  //申请结束时间
                    "(select e.ename from cu_emp e where e.empnum=r.empnum) empname," + //发布人姓名
                    "r.jobcontent,r.requirements,r.funclass,r.state from cu_promotion_release r <where> " +
            "<if test=\"rankQian!=null and rankQian!=''\">  rank > #{rankQian} </if> </where>" +
            "  order by r.id) b " +
            "where b.state in (${STATE}) and rownum &lt; #{end}) a where a.rn &gt; #{start}</script>")
    List<Map> getPageParam(Map map);
    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*)  from cu_promotion_release r where r.state in (${STATE})  " +
            "<if test=\"rankQian!=null and rankQian!=''\"> and rank > #{rankQian} </if> " +
            "</script>")
    int getPageCount(Map map);

    /**
     * 晋升信息的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_promotion_release(id,positionid,demandnum,endapply,empnum,jobcontent,requirements,rank) " +
            "values(seq_cu_pro_rel_id.nextval,#{POSITIONNAME},#{DEMANDNUM},to_date(substr(#{ENDAPPLY},1,10),'yyyy/mm/dd'),#{dempnum},#{JOBCONTENT},#{REQUIREMENTS},(select rank from cu_position where id = #{POSITIONNAME}))")
    int add(Map map);



    /**
     * 晋升信息的更新
     * @param map
     * @return
     */
    @Update(value = "update cu_promotion_release set POSITIONID=#{POSITIONID},DEMANDNUM=#{DEMANDNUM},ENDAPPLY=to_date(substr(#{ENDAPPLY},1,10),'yyyy/mm/dd')," +
            "JOBCONTENT=#{JOBCONTENT},REQUIREMENTS=#{REQUIREMENTS} where ID=#{ID}")
    int update(Map map);

    /**
     * 审核通过
     * @param map
     * @return
     */
    @Update(value = "update cu_promotion_apply set state = 1 where id = #{ID}")
    int updateTG(Map map);
    /**
     * 审核驳回
     * @param map
     * @return
     */
    @Update(value = "update cu_promotion_apply set state = 2, audtime = sysdate where id = #{ID}")
    int updateNoTG(Map map);
    /**
     * 员晋升信息的删除
     * @param id
     * @return
     */
    @Delete(value = "delete from cu_promotion_release where ID=#{id}")
    int delete(int id);

    /**
     * 根据id获取晋升发布信息
     * @return
     */
    int getPositionById(int id);

    /**
     * 晋升的角色查询
     * @return
     */
    @Select(value = "select POSITIONNAME,ID,RANK from cu_position where RANK > 1 and RANK < 98")
    List<Map> selectPosition();

    /**
     * 查询审核管理页面
     * @param map
     * @return
     */
    @Select("<script>" +
            "select * from " +
            "(select rownum rn,s.positionid,e.empnum,e.ename,e.telephone,e.emiladdr,p.state,p.id," +
            "(select positionname from cu_position where ID=e.position) positionname," +
            "(select positionname from cu_position where ID=p.beforepid) beforepname,"+
            "(select dname from dept where id=(select deptid from cu_group where gid=e.gid)) dname," +
            "(select positionname from cu_position where ID=s.positionid) applyname " +
            "from cu_emp e,cu_promotion_apply p,cu_promotion_release s " +
           " where e.empnum=p.applyemp and p.rid=s.id and p.state in(${STA}) and rownum &lt; #{end} " +
            "<if test=\"STATE!=null and STATE!=''\"> and p.STATE = #{STATE}</if>" +
            "<if test=\"STATE!=null and STATE!=''\"> and p.STATE = #{STATE}</if>" +
            ") a where a.rn &gt; #{start} </script>")
    List<Map> auditPromote(Map map);
    /**
     * 查询审核管理总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) from cu_promotion_apply p where p.state in (${STA})</script>")
    int auditPromoteCount(Map map);

    /**
     * 查询所有的正在发布中的晋升信息的id
     * @return
     */
    @Select(value = "select id from cu_promotion_release")
    List<Map> selectID();

    /**
     * 根据id查询当前已经审核的数量
     * @param id
     * @return
     */
    @Select(value = "select count(rid) count from cu_promotion_apply  where rid=#{id} and state=1")
    Map selectCount(int id);

    /**
     * 根据id查询所需人数
     * @param id
     * @return
     */
    @Select(value = "select demandnum,to_char(endapply,'yyyy-MM-dd') endapply from cu_promotion_release where id = #{id}")
    Map selectDemandnum(int id);

    /**
     * 当审核成功数量大于等于当前所需人数，根据晋升信息的id修改晋升信息的状态
     * @param id
     * @return
     */
    @Update(value = "update cu_promotion_release set state = 2 where id = #{id}")
    int updateToOK(int id);

    /**
     * 申请晋升
     * @param map
     * @return
     */
    @Insert(value = "insert into CU_PROMOTION_APPLY(id,rid,APPLYEMP,APPTIME,BEFOREPID) values(seq_cu_promotion_apply_id.nextval,#{id},#{dempnum},sysdate,#{pid})")
    int apply(Map map);

    /**
     * 查询当前empnum有无申请国晋升
     * @param map
     * @return
     */
    @Select(value = "select  id from CU_PROMOTION_APPLY where applyemp = #{dempnum} and state = 0")
    List<Map> selectPromote(Map map);
    /**
     * 修改empz职位
     */
    @Update( value = "update cu_emp set position = #{POSITIONID} where empnum = #{EMPNUM}")
    int updateToPosition(Map map);
}
