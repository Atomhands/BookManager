package com.niehao.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtil {

    private final static ThreadLocal<Connection> local = new ThreadLocal<>();

    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        try {
            dataSource.setUsername("scott");
            dataSource.setPassword("123456");
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
            dataSource.setUrl("jdbc:oracle:thin:@192.168.172.3:1521:orcl");
            dataSource.setInitialSize(5);
            dataSource.setMaxActive(10);
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void set() throws Exception {
        DruidPooledConnection conn = dataSource.getConnection();
        local.set(conn);
    }

    public static Connection get() {
        return local.get();
    }

    public static void remove() {
        try {
            get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            local.remove();
        }
    }

    /*事务*/
    public static void setAutoCommit(boolean commit) throws Exception {
        get().setAutoCommit(commit);
    }

    public static void commit() throws Exception {
        get().commit();
    }

    public static void rollback() {
        try {
            get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean autoCommit() {
        try {
            return get().getAutoCommit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
