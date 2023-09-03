package com.niehao.dao;

import com.niehao.dto.Page;
import com.niehao.pojo.Book;
import com.niehao.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public long selectCount() throws Exception {
        Connection conn = DataSourceUtil.get();
        String sql = "SELECT COUNT(*) FROM TB_BOOK_INFO ";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public List<Book> selectList(Page page) throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM " +
                "(SELECT A.*,ROWNUM RN FROM " +
                "(SELECT * FROM TB_BOOK_INFO ) A WHERE ROWNUM <= ?) B WHERE B.RN >?";
        Connection conn = DataSourceUtil.get();
        System.out.println(sql);
        PreparedStatement pst = conn.prepareStatement(sql);
        int s = (page.getCurrent() - 1) * page.getSize();
        int e = page.getCurrent() * page.getSize();
        pst.setInt(1, e);
        pst.setInt(2, s);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Book book = new Book(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6)
            );
            list.add(book);
        }
        return list;
    }

    public void insert(Book book) throws Exception {
        Connection conn = DataSourceUtil.get();
        String sql = "INSERT INTO TB_BOOK_INFO VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1, book.getBookId());
        pst.setObject(2, book.getBookName());
        pst.setObject(3, book.getPublisher());
        pst.setObject(4, book.getAuthor());
        pst.setObject(5, book.getBookType());
        pst.setObject(6, book.getRemain());
        pst.executeUpdate();
    }

    public void deleteBatch(List<String> ids) throws Exception {
        Connection conn = DataSourceUtil.get();
        // 开启事务, 多次删除 一次事务,提升性能
        DataSourceUtil.setAutoCommit(false);
        String sql = "DELETE FROM TB_BOOK_INFO WHERE BOOK_ID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        for (String id : ids) {
            pst.setString(1, id);
            pst.addBatch();
        }
        pst.executeBatch();
    }

    public void updateById(Book book) throws Exception {
        Connection conn = DataSourceUtil.get();
        String sql = "UPDATE TB_BOOK_INFO SET BOOK_NAME=?,PUBLISHER = ? , AUTHOR = ? , BOOK_TYPE = ? ,REMAIN = ? WHERE BOOK_ID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1, book.getBookName());
        pst.setObject(2, book.getPublisher());
        pst.setObject(3, book.getAuthor());
        pst.setObject(4, book.getBookType());
        pst.setObject(5, book.getRemain());
        pst.setObject(6, book.getBookId());
        pst.executeUpdate();
    }
}
