package app.wangzc.challenge.controllers.conf;

import java.sql.*;

/**
 * 数据库配置信息
 *
 * @author WangZC
 */

public class DatabaseConf {
	// 封装连接数据库的参数
	private static String url = "jdbc:mysql://localhost:3306/self_challenge?useUnicode=true&characterEncoding=utf-8";   // 使用Unicode编码，字符编码为UTF-8
	private static String user = PrivateConfig.user;
	private static String password = PrivateConfig.password;

	// 禁止产生实例对象
	private DatabaseConf() {
	}

	/*
	 静态块
	 说明：静态块将优先于构造块和构造方法执行，而且不管实例化多少个对象，静态块只执行一次
	*/
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("No suitable driver found");
		}
	}

	// 连接数据库
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	// 释放资源
	public static void release(ResultSet resultSet, Statement statement, Connection conn) {
		try {
			if (resultSet != null) {    // 这步不写，如果 resultSet 为 null, 会出现 java.lang.NullPointerException 错误
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
