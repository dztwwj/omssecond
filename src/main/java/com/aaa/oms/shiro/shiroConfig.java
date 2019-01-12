package com.aaa.oms.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * className:shiroConfig
 * discription:
 * author:HPY
 * createTime:2018-12-22 09:46
 */
@Configuration
public class shiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shrio内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器
         *          anon:无需认证（登录）可以访问
         *          authc:必须认证才可以访问
         *          user:如果使用rememberMe的功能可以直接访问
         *          perms:该资源必须得到资源权限才可以访问
         *          role:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/
        /**
         * toLogin、indexht放行
         *
         *
         */

        filterMap.put("/toLogin","anon");
        //放行indexht页面
        filterMap.put("/indexht","anon");
        filterMap.put("/qiantai","anon");

        filterMap.put("/recruit/employ","anon");
        filterMap.put("/recruit/page","anon");
        filterMap.put("/dept/list","anon");
        filterMap.put("/employ/addEmploy","anon");

        filterMap.put("/images/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/images/**","anon");
        //filterMap.put("/*","authc");
        //filterMap.put("/*/*","anon");

        //拦截所有请求
        filterMap.put("/**","authc");
        //修改跳转页面
        //shiroFilterFactoryBean.setLoginUrl("/shiro/toLogin");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     * @return
     */
    /*@Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }*/
}
