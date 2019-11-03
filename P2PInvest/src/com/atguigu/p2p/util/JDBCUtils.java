package com.atguigu.p2p.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/*
 * 操作JDBC的工具类
 */
public class JDBCUtils {

	// 关闭相关的资源
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 关闭相关的资源
	public static void close(Connection conn, PreparedStatement ps) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 获取数据库的连接
	public static Connection getConnection() throws Exception {
		// 1.从配置文件中读取连接的基本信息
		Properties pros = new Properties();
		// 默认加载的文件就在src下
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream(
				"jdbc.properties");
		pros.load(is);
		// 读取4个基本信息
		String userName = pros.getProperty("userName");
		String password = pros.getProperty("password");
		String driverClass = pros.getProperty("driverClass");
		String url = pros.getProperty("url");

		// 2.加载驱动
		Class.forName(driverClass);

		// 3.获取连接
		Connection conn = DriverManager.getConnection(url, userName, password);

		return conn;
	}

}
