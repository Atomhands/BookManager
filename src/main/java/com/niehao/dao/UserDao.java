package com.niehao.dao;

import com.niehao.pojo.User;
import com.niehao.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserDao {
    public User selectAccount(String account) throws Exception {
        User user = null;
        Connection conn = DataSourceUtil.get();
        String sql = "SELECT * FROM TB_USER WHERE ACCOUNT = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, account);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getString(7)
            );
        }

        return user;
    }

    public User selectPhone(String phone) throws Exception {
        User user = null;
        Connection conn = DataSourceUtil.get();
        String sql = "SELECT * FROM TB_USER WHERE PHONE = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, phone);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getString(7)
            );
        }

        return user;
    }

    public void insert(User user) throws Exception {
        Connection conn = DataSourceUtil.get();
        String sql = "INSERT INTO TB_USER VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user.getUserId());
        pst.setString(2, user.getAccount());
        pst.setString(3, user.getPassword());
        pst.setString(4, user.getName());
        pst.setString(5, user.getPhone());
        pst.setTimestamp(6, new Timestamp(user.getCreateTime().getTime()));
        pst.setString(7, user.getActive());
        pst.executeUpdate();
    }
}
