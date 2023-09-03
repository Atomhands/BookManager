package com.niehao.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String viewName;
        if (url.startsWith("/views")) {
            viewName = "/WEB-INF" + url;
        } else {
            viewName = "/WEB-INF/views" + url;
        }
        req.getRequestDispatcher(viewName).forward(req, resp);
    }

}
