package com.dotchat.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcUtil {
	private static Properties properties = new Properties();
	static {
		try {
			// 配置资源文件
			properties.load(JdbcUtil.class.getResourceAsStream("/db.properties"));
			// 加载驱动
			Class.forName(properties.getProperty("driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 *
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
			Logger.getLogger("JdbcUtil").info("已经连接到数据库");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static void close(Connection conn, Statement st, ResultSet rs) {

		try {
			if (conn != null) {
				conn.close();
			}
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
