package com.dotchat.db.dao.impl;

import com.dotchat.bean.UserInfo;
import com.dotchat.db.dao.UserInfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAOImplement implements UserInfoDAO {
    @Override
    public void save(Connection connection, UserInfo userInfo) throws SQLException {
        String sql = "insert into dotchat.user_info (uid,username,profile,sex,age,phone,email) values (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userInfo.getUid());
        statement.setString(2, userInfo.getUsername() != null ? userInfo.getUsername() : "");
        statement.setString(3, userInfo.getProfile() != null ? userInfo.getProfile() : "");
        statement.setString(4, userInfo.getSex() != null ? userInfo.getSex() : "");
        statement.setInt(5, userInfo.getAge());
        statement.setString(6, userInfo.getPhone() != null ? userInfo.getPhone() : "");
        statement.setString(7, userInfo.getEmail() != null ? userInfo.getEmail() : "");
        statement.execute();
    }

    @Override
    public int queryUserId(Connection connection, int uid) throws SQLException {
        String sql = "select * from dotchat.user_info where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setInt(1, uid);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return uid;
        } else {
            return 0;
        }
    }

    @Override
    public UserInfo query(Connection connection, int uid) throws SQLException {
        String sql = "select * from dotchat.user_info where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setInt(1, uid);

        ResultSet set = statement.executeQuery();
        if (set.next()) {
            int userId = set.getInt("uid");
            String username = set.getString("username");
            String profile = set.getString("profile");
            String sex = set.getString("sex");
            int age = set.getInt("age");
            String phone = set.getString("phone");
            String email = set.getString("email");

            UserInfo userInfo = new UserInfo();
            userInfo.setUid(uid);
            if (username != null) {
                userInfo.setUsername(username);
            }
            if (profile != null) {
                userInfo.setProfile(profile);
            }
            if (sex != null) {
                userInfo.setSex(sex);
            }
            userInfo.setAge(age);
            if (phone != null) {
                userInfo.setPhone(phone);
            }
            if (email != null) {
                userInfo.setEmail(email);
            }
            return userInfo;
        } else {
            return null;
        }
    }

    @Override
    public void updateSex(Connection connection, int uid, String sex) throws SQLException {
        String sql = "update dotchat.user_info set sex = ? where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, sex);
        statement.setInt(2, uid);
        statement.execute();
    }

    @Override
    public void updateAge(Connection connection, int uid, int age) throws SQLException {
        String sql = "update dotchat.user_info set age = ? where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setInt(1, age);
        statement.setInt(2, uid);
        statement.execute();
    }

    @Override
    public void updateProfile(Connection connection, int uid, String profile) throws SQLException {
        String sql = "update dotchat.user_info set profile = ? where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, profile);
        statement.setInt(2, uid);
        statement.execute();
    }

    @Override
    public void updatePhone(Connection connection, int uid, String phone) throws SQLException {
        String sql = "update dotchat.user_info set phone = ? where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, phone);
        statement.setInt(2, uid);
        statement.execute();
    }

    @Override
    public void updateEmail(Connection connection, int uid, String email) throws SQLException {
        String sql = "update dotchat.user_info set email = ? where uid = ?";
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, email);
        statement.setInt(2, uid);
        statement.execute();
    }

}
