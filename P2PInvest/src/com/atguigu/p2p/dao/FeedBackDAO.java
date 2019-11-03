package com.atguigu.p2p.dao;

import java.sql.Connection;

import com.atguigu.p2p.bean.FeedBack;
import com.atguigu.p2p.bean.User;

public class FeedBackDAO extends DAO<FeedBack>{
	
	/*
	 * 将指定的对象添加到数据表中
	 */
	public void insert(Connection conn,FeedBack feedBack){
		String sql = "insert into feedback_table(department,content) values(?,?)";
		update(conn, sql,feedBack.getDepartment(),feedBack.getContent());
		
	}
	
}
