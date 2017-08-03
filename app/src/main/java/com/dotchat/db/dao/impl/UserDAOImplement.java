package com.dotchat.db.dao.impl;

import com.dotchat.bean.User;
import com.dotchat.db.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImplement implements UserDAO {

	@Override
	public void save(Connection connection, User user) throws SQLException {
		String sql = "insert into dotchat.user (username,password) values (?,?)";
		PreparedStatement statement = connection.prepareCall(sql);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.execute();
	}

	@Override
	public int queryUserName(Connection connection, String username) throws SQLException {
		String sql = "select * from dotchat.user where username = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet set = statement.executeQuery();
		if(set.next()){
			return set.getInt("_id");
		}else{			
			return 0;
		}
	}

	@Override
	public int queryPassword(Connection connection, int id, String password) throws SQLException {
		String sql = "select * from dotchat.user where _id = ? && password = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setString(2, password);
		ResultSet set = statement.executeQuery();
		if(set.next()){
			return set.getInt("_id");
		}else{			
			return 0;
		}
	}

	@Override
	public void updateToken(Connection connection, int id, String token) throws SQLException {
		String sql = "update dotchat.user set token = ? where _id = ?";
		PreparedStatement statement = connection.prepareCall(sql);
		statement.setString(1, token);
		statement.setInt(2, id);
		statement.execute();
	}

	

}
