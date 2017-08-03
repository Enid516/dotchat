package com.dotchat.db.dao;

import com.dotchat.bean.UserInfo;

import java.sql.Connection;
import java.sql.SQLException;


public interface UserInfoDAO {
	public void save(Connection connection, UserInfo userInfo) throws SQLException;
	
	public int queryUserId(Connection connection, int uid) throws SQLException;

	public UserInfo query(Connection connection, int uid) throws SQLException;
	
	public void updateSex(Connection connection, int uid, String sex) throws SQLException;

	public void updateAge(Connection connection, int uid, int age) throws SQLException;

	public void updateProfile(Connection connection, int uid, String profile) throws SQLException;

	public void updatePhone(Connection connection, int uid, String phone) throws SQLException;

	public void updateEmail(Connection connection, int uid, String email) throws SQLException;

}
