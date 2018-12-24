package com.aaa.oms.entity;

/**
 * className:User
 * discription:
 * author:HPY
 * createTime:2018-12-22 11:17
 */
public class User {

    private Integer eid;
    private String empnum;
    private String epassword;


    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum;
    }

    public String getEpassword() {
        return epassword;
    }

    public void setEpassword(String epassword) {
        this.epassword = epassword;
    }
}
