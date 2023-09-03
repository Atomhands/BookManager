package com.niehao.pojo;

import java.util.Date;

public class User {
    private String userId; // UUID
    private String account;
    private String password;
    private String name;
    private String phone;
    private Date createTime; //
    private String active; // Y

    public User() {
    }

    public User(String account, String password, String name, String phone) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User(String userId, String account, String password, String name, String phone, Date createTime, String active) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.createTime = createTime;
        this.active = active;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
