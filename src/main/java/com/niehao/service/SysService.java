package com.niehao.service;

import com.niehao.dao.AdminDao;
import com.niehao.pojo.Admin;

public class SysService {

    private AdminDao adminDao;

    {
        adminDao = new AdminDao();
    }


    public Admin findAdminAccount(String account) throws Exception {
        return adminDao.selectAccount(account);
    }

    public Admin findById(String adminId) throws Exception {
        return adminDao.selectById(adminId);
    }
}
