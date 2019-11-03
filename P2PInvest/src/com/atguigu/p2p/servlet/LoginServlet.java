package com.atguigu.p2p.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.p2p.bean.User;
import com.atguigu.p2p.dao.UserDAO;
import com.atguigu.p2p.util.JDBCUtils;
import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {

	// public static String username = "13012341234";
	// // MD5加密字符串(密码是123456)
	// public static String password = "e10adc3949ba59abbe56e057f20f883e";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取请求参数
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		// 2.查询数据库，判断是否存在指定用户名和密码的用户
		UserDAO userDAO = new UserDAO();
		// 2.1 获取数据库的连接
		Connection conn = null;
		OutputStream os = null;
		try {
			conn = JDBCUtils.getConnection();
			// 2.2 查询数据表，得到数据
			User user = userDAO.getInstance(conn, phone, password);
			os = response.getOutputStream();
			if (user != null) {// 3.返回值不为null,表示登录成功
				String json = "{\"data\": {\"name\": \"" + user.getName()
						+ "\",\"imageurl\": \"" + user.getImageurl()
						+ "\",\"iscredit\": \"" + user.isCredit()
						+ "\",\"phone\":\"" + user.getPhone()
						+ "\"},\"success\": true}";
				System.out.println(json + "!!!");
				// 返回登录信息
				os.write(json.getBytes());
				os.flush();
			} else {
				// 登录失败(用户名不存在或密码不正确)
				String jsonError = "{\"success\":false}";
				os.write(jsonError.getBytes());
				os.flush();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
			JDBCUtils.close(conn, null);
		}

	}

}
