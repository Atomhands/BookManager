package com.niehao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.HttpResult;
import com.niehao.pojo.Admin;
import com.niehao.service.SysService;
import com.niehao.utils.EncryptUtil;

import javax.servlet.http.HttpSession;

public class SysController {

    private SysService sysService;

    {
        sysService = new SysService();
    }

    public HttpResult login(Admin admin, HttpSession session) throws Exception {
        Admin data = sysService.findAdminAccount(admin.getAccount());
        // 3. 验证登录
        if (ObjectUtil.isEmpty(data)) {
            throw new RuntimeException("用户账号不存在");
        }
        if (!StrUtil.equals(data.getPassword(), EncryptUtil.md5(admin.getPassword()))) {
            throw new RuntimeException("密码错误");
        }
        // 保存会话 ： 保存到 session 中
        session.setAttribute("ADMIN_ID", data.getAdminId());
        session.setAttribute("ADMIN_NAME", data.getName());
        session.setAttribute("ROLE_NAME", data.getRoleName());

        return new HttpResult(true, "登录成功", null, 2000);
    }

    public HttpResult getInfo(String adminId) throws Exception {
        Admin data = sysService.findById(adminId);
        return new HttpResult(true, "获取管理员", data, 2000);
    }
}
