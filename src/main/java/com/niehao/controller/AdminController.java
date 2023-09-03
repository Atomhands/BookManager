package com.niehao.controller;

import com.niehao.dto.DataGrid;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Admin;
import com.niehao.service.AdminService;
import com.niehao.utils.EncryptUtil;
import com.niehao.utils.IdGenerate;

import java.util.Date;

public class AdminController {

    private AdminService adminService;

    {
        adminService = new AdminService();
    }

    public HttpResult saveAdmin(Admin admin) throws Exception {
        admin.setPassword(EncryptUtil.md5("123456"));
        admin.setAccount(admin.getPhone());
        admin.setAdminId(IdGenerate.uuid());
        admin.setCreateTime(new Date());
        admin.setRoleName("USER");
        adminService.saveAdmin(admin);
        return HttpResult.OK("录入管理员", null);
    }

//    public HttpResult findRoles() throws Exception {
//        List<String> roles = adminService.findRoles();
//        return HttpResult.OK("获取角色列表", roles);
//    }

//    public HttpResult checkAccount(String phone) throws Exception {
//        adminService.checkAccount(phone);
//        return HttpResult.OK("验证手机号", 2000);
//    }


    public DataGrid findList(Page page, Admin admin) throws Exception {
        page.setCurrent(page.getCurrent() + 1);
        return adminService.findList(page, admin);
    }
}
