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
    private String ename;
    private Integer iseffective;
    private String position;

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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getIseffective() {
        return iseffective;
    }

    public void setIseffective(Integer iseffective) {
        this.iseffective = iseffective;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
