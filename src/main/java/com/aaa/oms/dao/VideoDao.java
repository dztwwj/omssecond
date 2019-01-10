package com.aaa.oms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:VideoDao
 * discription:视频管理接口
 * author:zl
 * createTime:2018-12-18 08:25
 */

public interface VideoDao {

    /**
     *视频查询
     * @return
     */

    @Select("<script> select vid,vname,uploaddate,describe,videopath,picpath,vstate from cu_video \n"+
            "<where><if test=\"vname!=null and vname!=''\"> vname like '%'||#{vname}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 视频的添加
     * @param map
     * @return
     */
    @Insert(value = "insert into cu_video values(seq_dept_id.nextval,#{VNAME},sysdate,#{DESCRIBE},#{VIDEOPATH},#{PICPATH},#{VSTATE})")
    int add(Map map);

    /**
     * 课程的更新
     * @param map
     * @return
     */
    @Update(value = "update cu_video set vname=#{VNAME},uploaddate=sysdate,describe=#{DESCRIBE},videopath=#{VIDEOPATH},picpath=#{PICPATH},vstate=#{VSTATE} where vid=#{VID}")
    int update(Map map);

    /**
     * 部门的删除
     * @param vid
     * @return
     */
    @Delete(value = "delete from cu_video where vid=#{vid}")
    int delete(int vid);


    int batchDelete(List list);
}
