package com.atguigu.p2p.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.p2p.bean.User;
import com.atguigu.p2p.dao.UserDAO;
import com.atguigu.p2p.util.JDBCUtils;

/**
 * Servlet implementation class UserRegisterSevlet
 */
public class UserRegisterSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		Connection conn = null;
		OutputStream os = null;
		try {
			//判断是否已经注册
			UserDAO dao = new UserDAO();
			conn = JDBCUtils.getConnection();
			boolean isExist = dao.isExist(conn, phone);
			os = response.getOutputStream();
			//如果没有，则反馈注册成功
			if(!isExist){//没有注册过
				User user = new User(-1, name, password, phone);
				System.out.println(user);
				dao.insert(conn, user);
				os.write(("{\"isExist\":" + isExist + "}").getBytes());
				
			}else{//已经注册过
				os.write(("{\"isExist\":" + isExist + "}").getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				os.close();
			}
			JDBCUtils.close(conn, null);
		}
	}

}
