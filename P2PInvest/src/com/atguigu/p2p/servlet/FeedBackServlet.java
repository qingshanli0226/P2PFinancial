package com.atguigu.p2p.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.p2p.bean.FeedBack;
import com.atguigu.p2p.dao.FeedBackDAO;
import com.atguigu.p2p.util.JDBCUtils;

/**
 */
public class FeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doPost(request, response);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String department = request.getParameter("department");
		String content = request.getParameter("content");
		
		FeedBack feedBack = new FeedBack(-1, department, content);
		System.out.println(feedBack);
		FeedBackDAO dao = new FeedBackDAO();
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			dao.insert(conn, feedBack);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, null);
			
		}
		
	}

}
