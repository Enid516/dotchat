package com.dotchat.service;

import com.dotchat.bean.BaseResult;
import com.dotchat.bean.LoginResult;
import com.dotchat.bean.User;
import com.dotchat.bean.UserInfo;
import com.dotchat.db.JdbcUtil;
import com.dotchat.db.dao.UserDAO;
import com.dotchat.db.dao.UserInfoDAO;
import com.dotchat.db.dao.impl.UserDAOImplement;
import com.dotchat.db.dao.impl.UserInfoDAOImplement;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService extends BaseService{

	public BaseResult register(String username, String password, String passwordr) {
		BaseResult result = new BaseResult();
		Connection connection = JdbcUtil.getConnection();
		UserDAO userDAO = new UserDAOImplement();
		try {
			// 1.判断用户名是否已经被占用
			int id = userDAO.queryUserName(connection, username);
			if (id != 0) {
				return setResult(result, RESULT_CODE_FALIED, "用户名已经被占用");
			}

			// 2.检查用户名合法性，不能为空，长度不能超过8个字符
			if(username == null || username.length() == 0){
				return setResult(result, RESULT_CODE_FALIED, "用户名不能为空");
			}
			if(username.length() > 8){
				return setResult(result, RESULT_CODE_FALIED, "用户名长度不能超过8个字符");
			}

			// 3.检查密码的合法性，不能为空，长度应在6-20之间包含6和20
			if(password == null || password.length() == 0){
				return setResult(result, RESULT_CODE_FALIED, "密码不能为空");
			}
			if (password.length() < 6 || password.length() > 20) {
				return setResult(result, RESULT_CODE_FALIED, "密码必须是6-20个英文字母、数字字符的组合");
			}

			// 4.判断两次密码输入是否一致
			if(!password.equals(passwordr)){
				return setResult(result, RESULT_CODE_FALIED, "两次输入的密码不一致");
			}

			// 5.保存User到数据库
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userDAO.save(connection, user);

			// 6.保存UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setUid(userDAO.queryUserName(connection, username));
			userInfo.setUsername(username);
			UserInfoDAO infoDAO = new UserInfoDAOImplement();
			infoDAO.save(connection, userInfo);
			return setResult(result, RESULT_CODE_SUCCESS, "注册成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public BaseResult login(String username, String password) {
		BaseResult result = new LoginResult();
		Connection connection = JdbcUtil.getConnection();
		UserDAO userDAO = new UserDAOImplement();
		try {
			// 1.判断用户是否存在
			int id = userDAO.queryUserName(connection, username);
			if (id == 0) {
				return setResult(result, RESULT_CODE_FALIED, "用户不存在");
			}

			// 2.判断密码是否正确
			int uid = userDAO.queryPassword(connection, id, password);
			if (uid == 0) {
				return setResult(result, RESULT_CODE_FALIED, "密码错误");
			}

			// 3.设置token
			String token = uid + "_" + System.currentTimeMillis();
			result.setToken(token);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("登录成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public BaseResult logout(String username){
		BaseResult result = new BaseResult();
		Connection connection = JdbcUtil.getConnection();
		UserDAO userDAO = new UserDAOImplement();

		try {
			// 1.判断用户是否存在
			int uid = userDAO.queryUserName(connection, username);
			if (uid == 0){
				return setResult(result,RESULT_CODE_FALIED,ERROR_MSG.USER_DONT_EXISTED);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED,ERROR_MSG.DATABASE_ERROR);
		}
		return setResult(result,RESULT_CODE_SUCCESS,"注销成功");
	}

}
