package com.dotchat.db.dao;

import com.dotchat.bean.User;

import java.sql.Connection;
import java.sql.SQLException;


public interface UserDAO {
	/**
	 * 将User保存到数据库
	 *
	 * @param connection
	 * @param user
	 * @throws SQLException
	 */
	void save(Connection connection, User user) throws SQLException;


	/**
	 * 查询数据库中是否有指定的userName,有的话返回对应的id，否则返回0
	 *
	 * @param connection
	 * @param username
	 * @throws SQLException
	 */
	int queryUserName(Connection connection, String username) throws SQLException;

	/**
	 * 查询数据库中指定id用户的密码是否正确，正确的话返回id,否则返回0
	 *
	 * @param connection
	 * @param id
	 * @param password
	 * @throws SQLException
	 */
	int queryPassword(Connection connection, int id, String password) throws SQLException;

	/**
	 * 更新指定id 的User中的token
	 * @param connection
	 * @param id
	 * @param token
	 * @throws SQLException
	 */
	void updateToken(Connection connection, int id, String token) throws SQLException;

}
