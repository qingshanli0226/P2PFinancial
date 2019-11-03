package com.atguigu.p2p.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.atguigu.p2p.bean.User;
import com.atguigu.p2p.util.JDBCUtils;

public class UserDAOTest {

	@Test
	public void testConnection() {
		try {
			Connection conn = JDBCUtils.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		UserDAO dao = new UserDAO();
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			List<User> list = dao.getAll(conn);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtils.close(conn, null);

		}

	}
}
