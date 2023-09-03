package com.niehao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Admin {
    private String adminId; // ADMIN_ID
    private String account;
    private String password;
    private String name;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //
    private String roleName;

    public Admin() {
    }

    public Admin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Admin(String adminId, String account, String name, String phone, Date createTime, String roleName) {
        this.adminId = adminId;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.createTime = createTime;
        this.roleName = roleName;
    }

    public Admin(String account, String name, String phone, Date createTime, String roleName) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.createTime = createTime;
        this.roleName = roleName;
    }

    public Admin(String account, String password, String name, String phone) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public Admin(String adminId, String account, String password, String name, String phone, Date createTime, String roleName) {
        this.adminId = adminId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.createTime = createTime;
        this.roleName = roleName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
