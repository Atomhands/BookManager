package com.niehao.service;

import com.niehao.dao.BookDao;
import com.niehao.dto.DataGrid;
import com.niehao.dto.Page;
import com.niehao.pojo.Book;

import java.util.List;

public class BookService {

    private BookDao bookDao;

    {
        bookDao = new BookDao();
    }

    public DataGrid findList(Page page) throws Exception {
        // 查询 数量
        long total = bookDao.selectCount();
        // 查询 数据
        List<Book> books = bookDao.selectList(page);
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(total);
        dataGrid.setData(books);
        return dataGrid;
    }

    public void saveBook(Book book) throws Exception {
        bookDao.insert(book);
    }

    public void remove(List<String> ids) throws Exception {
        bookDao.deleteBatch(ids);
    }

    public void editBook(Book book) throws Exception {
        bookDao.updateById(book);
    }
}
