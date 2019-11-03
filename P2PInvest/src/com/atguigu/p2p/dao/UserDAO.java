package com.atguigu.p2p.dao;


import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.atguigu.p2p.bean.User;

public class UserDAO extends DAO<User>{
	
	
	/*
	 * 查询数据表中一共多少条记录
	 */
	public long getCount(Connection conn){
		String sql = "select count(*) from user_table";
		//返回的记录数是Long
		return (Long)getValue(conn, sql);
	}
	
	
	/*
	 * 查询对应的数据表中的所有记录
	 */
	public List<User> getAll(Connection conn){
		String sql = "select id,name,password,imageurl,iscredit from user_table";
		List<User> list = getForList(conn, sql);
		return list;
	}
	
	/*
	 * 查询指定phone和password的用户
	 */
	public User getInstance(Connection conn,String phone,String password){
		String sql = "select id,name,phone,imageurl,iscredit from user_table where phone = ? and password = ?";
		User cust = getInstance(conn, sql, phone,password);
		System.out.println("cust  = " + cust);
		return cust;
	}
	/**
	 * 查询指定的phone的用户是否存在
	 * @param conn
	 * @param phone
	 * @return false:不存在。  true:存在
	 */
	public boolean isExist(Connection conn,String phone){
		String sql = "select phone from user_table where phone = ?";
		User cust = super.getInstance(conn,sql,phone);
		if(cust == null){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * 修改指定id（user的属性id值）的数据表中的记录，改为user的属性值。
	 * 
	 */
	public void update(Connection conn,User user){
		String sql = "update user_table set name = ,password = ?,imageurl = ? where id = ?";
		update(conn, sql,user.getName(),user.getPassword(),user.getImageurl(),user.getId());
	}
	/*
	 * 删除指定用户名的数据表中的记录
	 */
	public void deleteByName(Connection conn,String name){
		String sql = "delete from user_table where name = ?";
		update(conn, sql, name);
	}
	/*
	 * 删除指定id的数据表中的记录
	 */
	public void delete(Connection conn,int id){
		String sql = "delete from user_table where id = ?";
		update(conn, sql, id);
	}

	/*
	 * 将指定的对象添加到数据表中
	 */
	public void insert(Connection conn,User user){
		String sql = "insert into user_table(name,password,phone) values(?,?,?)";
		update(conn, sql,user.getName(),user.getPassword(),user.getPhone());
		
	}
}
