package com.aaa.oms.shiro;

import com.aaa.oms.entity.User;
import com.aaa.oms.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * className:UserRealm
 * discription:
 * author:HPY
 * createTime:2018-12-22 09:48
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }


    //依赖注入
    @Autowired
    private LoginService loginService;

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
        /*String empnum = "eric";
        String epassword = "123456";*/

        //编写shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = loginService.findByName(token.getUsername());
        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        return new SimpleAuthenticationInfo("",user.getEpassword(),"");
    }
}
