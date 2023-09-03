package com.niehao.service;

import com.niehao.dao.AdminDao;
import com.niehao.dto.DataGrid;
import com.niehao.dto.Page;
import com.niehao.pojo.Admin;

import java.util.List;


public class AdminService {

    private AdminDao adminDao;

    {
        adminDao = new AdminDao();
    }

//    public List<String> findRoles() throws Exception {
//        return adminDao.selectRoles();
//    }

    public void checkAccount(String phone) throws Exception {
        Admin admin = adminDao.selectPhone(phone);
        if (admin != null) {
            throw new RuntimeException("手机号已注册");
        }
    }

    public void saveAdmin(Admin admin) throws Exception {
        // 等待优化
        adminDao.insert(admin);
    }

    public DataGrid findList(Page page, Admin admin) throws Exception {
        long total = adminDao.selectCount(admin);
        List<Admin> list = adminDao.selectList(page, admin);

        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(total);
        dataGrid.setData(list);
        return dataGrid;
    }
}
