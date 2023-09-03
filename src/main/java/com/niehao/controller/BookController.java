package com.niehao.controller;

import com.niehao.dto.DataGrid;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Book;
import com.niehao.service.BookService;
import com.niehao.utils.IdGenerate;

import java.util.List;

public class BookController {

    private BookService bookService;

    {
        bookService = new BookService();
    }

    public DataGrid findList(Page page) throws Exception {
        page.setCurrent(page.getCurrent() + 1);
        return bookService.findList(page);
    }

    public HttpResult saveBook(Book book) throws Exception {
        book.setBookId(IdGenerate.uuid());
        bookService.saveBook(book);
        return HttpResult.OK("新增图书", null);
    }

    public HttpResult removeBook(List<String> ids) throws Exception {
        bookService.remove(ids);
        return HttpResult.OK("删除选中", null);
    }

    public HttpResult editBook(Book book) throws Exception {
        bookService.editBook(book);
        return HttpResult.OK("编辑图书", null);
    }
}
