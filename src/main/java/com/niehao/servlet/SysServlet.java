package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import com.niehao.controller.SysController;
import com.niehao.dto.HttpResult;
import com.niehao.pojo.Admin;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sys/*")
public class SysServlet extends HttpServlet {

    private SysController sysController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sysController = new SysController();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().replace("/sys/", "");
        HttpResult result = null;
        try {
            // 拿数据库
            DataSourceUtil.set();

            // 目标业务
            if ("login".equals(url)) result = login(req);

            if ("info".equals(url)) result = getInfo(req);

            // commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();

            // 响应
            JSONUtil.writerJson(resp, result);

        } catch (Exception e) {
            // rollback : 当业务发生异常的时候
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();

            String msg = e.getLocalizedMessage();
            result = new HttpResult(false, msg, null, 4000);
            JSONUtil.writerJson(resp, result);
            e.printStackTrace();
        } finally {
            DataSourceUtil.remove();
        }
    }

    private HttpResult getInfo(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        // 这里肯定由 ID
        String adminId = Convert.toStr(session.getAttribute("ADMIN_ID"));
        return sysController.getInfo(adminId);
    }

    private HttpResult login(HttpServletRequest req) throws Exception {
        /*
        account: admin
        password: 1111
         */
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        Admin admin = new Admin(account, password);

        return sysController.login(admin, session);
    }
}
