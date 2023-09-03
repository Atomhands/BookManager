package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.niehao.controller.AdminController;
import com.niehao.dto.DataGrid;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Admin;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminController adminController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        adminController = new AdminController();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().replace("/admin/", "");
        Object result = null;
        try {
            DataSourceUtil.set();
            if (StrUtil.equals("saveAdmin", url)) result = saveAdmin(req, resp);

            if (StrUtil.equals("checkPhone", url)) result = checkPhone(req, resp);

            if (StrUtil.equals("list", url)) result = findList(req, resp);
            // commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
            // 响应
            JSONUtil.writerJson(resp, result);
        } catch (Exception e) {
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();
            String msg = e.getLocalizedMessage();
            result = new HttpResult(false, msg, null, 4000);
            JSONUtil.writerJson(resp, result);
            e.printStackTrace();
        } finally {
            DataSourceUtil.remove();
        }
    }

    private Object checkPhone(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("checkPhone");
        return true;
    }

    private HttpResult saveAdmin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /*
        name: zhangsan
        gender: 1
        phone: 222333
        birth: Thu Aug 03 2023 00:00:00 GMT+0800 (中国标准时间)
         */
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dateStr = req.getParameter("birth");
        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setName(name);
        return adminController.saveAdmin(admin);
    }

    private DataGrid findList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int current = Convert.toInt(req.getParameter("pageIndex"));
        int size = Convert.toInt(req.getParameter("pageSize"));
        String sortField = Convert.toStr(req.getParameter("sortField"));
        String sortOrder = Convert.toStr(req.getParameter("sortOrder"));
        Page page = new Page(current, size, sortField, sortOrder);
        String phone = Convert.toStr(req.getParameter("phone"));
        // ....
        // System.out.println(phone);
        Admin admin = new Admin();
        admin.setPhone(phone);
        // setter
        return adminController.findList(page, admin);
    }

}
