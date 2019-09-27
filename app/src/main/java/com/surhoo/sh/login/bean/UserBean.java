package com.surhoo.sh.login.bean;

public class UserBean {

    private String name;

    private String pwd;


    public UserBean() {
    }

    public UserBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
