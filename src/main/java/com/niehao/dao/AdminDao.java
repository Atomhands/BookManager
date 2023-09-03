package com.niehao.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.Page;
import com.niehao.pojo.Admin;
import com.niehao.utils.CaseUtil;
import com.niehao.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

public class AdminDao {

    public long selectCount(Admin admin) throws Exception {
        String sql = "SELECT COUNT(*) FROM TB_ADMIN ";

        // 将非空的值取出来
        Map<String, Object> map = new HashMap<>();
        BeanUtil.copyProperties(admin, map, CopyOptions.create().ignoreNullValue());
        if (MapUtil.isNotEmpty(map)) {
            sql += "WHERE ";
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            while (its.hasNext()) {
                Map.Entry<String, Object> next = its.next();
                String colum = CaseUtil.toUnderScoreCase(next.getKey()).toUpperCase();
                sql += colum + " = ? AND";
            }
            // 去掉最后一个 AND
            sql = sql.substring(0, sql.length() - 3);
        }

        System.out.println(sql);

        Connection conn = DataSourceUtil.get();

        PreparedStatement pst = conn.prepareStatement(sql);
        if (MapUtil.isNotEmpty(map)) {
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            int index = 1;
            while (its.hasNext()) {
                Object value = its.next().getValue();
                pst.setObject(index++, value);
            }
        }
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }


    public Admin selectAccount(String account) throws Exception {
        Connection conn = DataSourceUtil.get();
        Admin admin = null;
        String sql = "SELECT * FROM TB_ADMIN WHERE ACCOUNT = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, account);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            admin = new Admin(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getString(7)
            );
        }
        return admin;
    }

    public void updatePassword(String adminId, String password) throws Exception {
        String sql = "UPDATE TB_ADMIN SET PASSWORD = ? WHERE ADMIN_ID = ?";
        Connection conn = DataSourceUtil.get();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, password);
        pst.setString(2, adminId);
        pst.executeUpdate();
    }

    public Admin selectPhone(String phone) throws Exception {
        Connection conn = DataSourceUtil.get();
        Admin admin = null;
        String sql = "SELECT * FROM TB_ADMIN WHERE PHONE = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, phone);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            admin = new Admin(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getString(7)
            );
        }
        return admin;
    }

    public void insert(Admin admin) throws Exception {
        Connection conn = DataSourceUtil.get();
        String sql = "INSERT INTO TB_ADMIN VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, admin.getAdminId());
        pst.setString(2, admin.getAccount());
        pst.setString(3, admin.getPassword());
        pst.setString(4, admin.getName());
        pst.setString(5, admin.getPhone());
        pst.setTimestamp(6, new Timestamp(admin.getCreateTime().getTime()));
        pst.setString(7, admin.getRoleName());
        pst.executeUpdate();
    }

    public List<Admin> selectList() throws Exception {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT ADMIN_ID,ACCOUNT,NAME,PHONE,CREATE_TIME,ROLE_NAME FROM TB_ADMIN";
        Connection conn = DataSourceUtil.get();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Admin admin = new Admin(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getTimestamp(5),
                    rs.getString(6)
            );
            list.add(admin);
        }
        return list;
    }

    public Admin selectById(String adminId) throws Exception {
        Admin admin = null;
        String sql = "SELECT ACCOUNT,NAME,PHONE,CREATE_TIME,ROLE_NAME FROM TB_ADMIN WHERE ADMIN_ID = ?";
        Connection conn = DataSourceUtil.get();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, adminId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            admin = new Admin(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getTimestamp(4),
                    rs.getString(5)
            );
        }
        return admin;
    }

    public List<Admin> selectList(Page page, Admin admin) throws Exception {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT * FROM " +
                "(SELECT A.*,ROWNUM RN FROM " +
                "(SELECT ADMIN_ID,ACCOUNT,NAME,PHONE,CREATE_TIME,ROLE_NAME FROM TB_ADMIN ";

        // 将非空的值取出来
        Map<String, Object> map = new HashMap<>();
        BeanUtil.copyProperties(admin, map, CopyOptions.create().ignoreNullValue());
        if (MapUtil.isNotEmpty(map)) {
            sql += "WHERE ";
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            while (its.hasNext()) {
                Map.Entry<String, Object> next = its.next();
                String colum = CaseUtil.toUnderScoreCase(next.getKey()).toUpperCase();
                sql += colum + " = ? AND";
            }
            // 去掉最后一个 AND
            sql = sql.substring(0, sql.length() - 3);
        }


        if (StrUtil.isNotEmpty(page.getSortOrder()) && StrUtil.isNotEmpty(page.getSortField())) {
            sql += "ORDER BY " + page.getSortField() + " " + page.getSortOrder();
        }

        sql += ") A WHERE ROWNUM <= ?) B WHERE B.RN >?";


        System.out.println(sql);
        Connection conn = DataSourceUtil.get();
        PreparedStatement pst = conn.prepareStatement(sql);

        if (MapUtil.isNotEmpty(map)) {
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            int index = 1;
            while (its.hasNext()) {
                Object value = its.next().getValue();
                pst.setObject(index++, value);
            }
        }

        int s = (page.getCurrent() - 1) * page.getSize();
        int e = page.getCurrent() * page.getSize();
        int x = map.size();
        pst.setInt(x + 1, e);
        pst.setInt(x + 2, s);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Admin a = new Admin(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getTimestamp(5),
                    rs.getString(6)
            );
            list.add(a);
        }
        return list;
    }

}
