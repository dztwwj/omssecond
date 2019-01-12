package com.aaa.oms.dao;


import com.aaa.oms.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
//@CacheNamespace(implementation = RedisCache.class)
public interface LoginDao {

    User findByName(String empnum);

    @Select(value = "select * from cu_emp ")
    List<Map> getname(String ename);


}
