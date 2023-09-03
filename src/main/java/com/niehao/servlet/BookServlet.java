package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.niehao.controller.BookController;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Book;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {

    private BookController bookController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookController = new BookController();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().replace("/book/", "");
        Object result = null;
        try {
            DataSourceUtil.set();

            if (StrUtil.equals("list", url)) result = findList(req, resp);

            if (StrUtil.equals("add", url)) result = addBook(req, resp);

            if (StrUtil.equals("remove", url)) result = removeBook(req, resp);

            if (StrUtil.equals("edit", url)) result = editBook(req, resp);

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

    private HttpResult removeBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ids = req.getParameter("ids");
        List<String> list = Arrays.asList(ids.split(","));
        return bookController.removeBook(list);
    }

    private HttpResult addBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bookName = Convert.toStr(req.getParameter("bookName"));
        String bookType = Convert.toStr(req.getParameter("bookType"));
        String publisher = Convert.toStr(req.getParameter("publisher"));
        String author = Convert.toStr(req.getParameter("author"));
        int remain = Convert.toInt(req.getParameter("remain"));
        Book book = new Book(null, bookName, publisher, author, bookType, remain);
        return bookController.saveBook(book);
    }

    private HttpResult editBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bookName = Convert.toStr(req.getParameter("bookName"));
        String bookId = Convert.toStr(req.getParameter("bookId"));
        String bookType = Convert.toStr(req.getParameter("bookType"));
        String publisher = Convert.toStr(req.getParameter("publisher"));
        String author = Convert.toStr(req.getParameter("author"));
        int remain = Convert.toInt(req.getParameter("remain"));
        Book book = new Book(bookId, bookName, publisher, author, bookType, remain);
        return bookController.editBook(book);
    }

    private Object findList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int current = Convert.toInt(req.getParameter("pageIndex"));
        int size = Convert.toInt(req.getParameter("pageSize"));
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return bookController.findList(page);
    }
}
