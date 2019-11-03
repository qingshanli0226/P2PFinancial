package com.atguigu.p2p.dao;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.p2p.util.JDBCUtils;


/*
 * DAO: date(base) access object
 */
class ReflectUtils{
	
	/*
	 * 获取形参clazz所对应的父类的泛型
	 */
	public static Class getSuperClassGeneric(Class clazz){
		Type superclass = clazz.getGenericSuperclass();
		//强转为带泛型参数的Type类型
		ParameterizedType paramType = (ParameterizedType) superclass;
		//获取父类的泛型构成的数组
		Type[] actualTypeArguments = paramType.getActualTypeArguments();
		return (Class)actualTypeArguments[0];
		
	}
}

public class DAO<T> {
	
	private Class<T> clazz = null;
	
	public DAO(){
		//获取父类的泛型，并声明给Class
		clazz = ReflectUtils.getSuperClassGeneric(this.getClass());
	}
	
	//SELECT Max(name) FROM customers where id > ?;
	//查询特殊意义的结构
	public <E> E getValue(Connection conn,String sql,Object ... args){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			// 1.预编译sql语句
			ps = conn.prepareStatement(sql);
			// 2.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 3.获取结果集
			rs = ps.executeQuery();
			
			if (rs.next()) {// 对应一行数据
				return (E)rs.getObject(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5.关闭资源
			JDBCUtils.close(null, ps, rs);

		}

		return null;
	}
	
	
	public void m(){
		System.out.println(clazz);
	}

	// 通用的查询操作（返回多个java对象构成的集合）
	public List<T> getForList(Connection conn, String sql,
			Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {

			// 1.预编译sql语句
			ps = conn.prepareStatement(sql);
			// 2.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 3.获取结果集
			rs = ps.executeQuery();

			// 4.获取结果集的元数据：ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 4.1:可以获取结果集的列数
			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {// 对应一行数据
				// 创建Class对应的运行时类的对象
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 4.2取出每一列的值
					Object columnVal = rs.getObject(i + 1);
					// 4.3获取列的名字(即为java类的属性名)
					String columeLabel = rsmd.getColumnLabel(i + 1);
					// System.out.println(columnVal);

					// 使用反射装配属性
					Field field = clazz.getDeclaredField(columeLabel);
					field.setAccessible(true);
					field.set(t, columnVal);

				}
				
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5.关闭资源
			JDBCUtils.close(null, ps, rs);

		}

		return null;
	}

	// 通用的查询操作（返回一个java对象）
	public T getInstance(Connection conn,String sql,
			Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		T t = null;
		try {

			// 1.预编译sql语句
			ps = conn.prepareStatement(sql);
			// 2.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 3.获取结果集
			rs = ps.executeQuery();

			// 4.获取结果集的元数据：ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 4.1:可以获取结果集的列数
			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {// 对应一行数据
				// 创建Class对应的运行时类的对象
				t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 4.2取出每一列的值
					Object columnVal = rs.getObject(i + 1);
					// 4.3获取列的名字(即为java类的属性名)
					String columeLabel = rsmd.getColumnLabel(i + 1);
					// System.out.println(columnVal);

					// 使用反射装配属性
					Field field = clazz.getDeclaredField(columeLabel);
					field.setAccessible(true);
					field.set(t, columnVal);

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5.关闭资源
			JDBCUtils.close(null, ps, rs);

		}

		return t;
	}

	// 通用的增删改操作
	public int update(Connection conn, String sql, Object... args) {
		// 1.预编译sql语句
		PreparedStatement ps = null;
		int updateCount = 0;
		try {
			ps = conn.prepareStatement(sql);
			// 2.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			// 3.执行
			updateCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.关闭资源(不要关闭连接)
			JDBCUtils.close(null, ps, null);

		}

		return updateCount;

	}

}
