package com.aaa.oms.service;

import com.aaa.oms.entity.User;

/**
 * className:PowerController
 * discription:
 * author:HPY
 * createTime:2018-12-11 10:24
 */
public interface LoginService {
    public User findByName(String empnum);
}
