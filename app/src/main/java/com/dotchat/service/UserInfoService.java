package com.dotchat.service;

import com.dotchat.bean.BaseResult;
import com.dotchat.db.JdbcUtil;
import com.dotchat.db.dao.UserInfoDAO;
import com.dotchat.db.dao.impl.UserInfoDAOImplement;

import java.sql.Connection;
import java.sql.SQLException;

public class UserInfoService extends BaseService {

	public BaseResult updateProfile(Connection connection, int uid, String profile) {
		BaseResult result = new BaseResult();
		UserInfoDAO infoDAO = new UserInfoDAOImplement();
		try {
			// 1.判断用户是否存在
			if (isUserExisted(connection, infoDAO, uid)) {
				return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.USER_DONT_EXISTED);
			}
			// 2.检查用户头像地址是否为空
			if (profile == null || profile.length() == 0) {
				return setResult(result, RESULT_CODE_FALIED, "用户头像地址不能为空");
			}
			// 3.修改用户名
			infoDAO.updateProfile(connection, uid, profile);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("修改用户名成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public BaseResult updateSex(int uid, String sex) {
		Connection connection = JdbcUtil.getConnection();
		BaseResult result = new BaseResult();
		UserInfoDAO infoDAO = new UserInfoDAOImplement();
		try {
			// 1.判断用户是否存在
			if (!isUserExisted(connection, infoDAO, uid)) {
				return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.USER_DONT_EXISTED);
			}
			// 2.检查性别参数是否为空
			if (sex == null || sex.length() == 0) {
				return setResult(result, RESULT_CODE_FALIED, "性别不能为空");
			}
			// 3.修改用户性别
			infoDAO.updateSex(connection, uid, sex);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("修改成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public BaseResult updateAge(Connection connection, int uid, int age) {
		BaseResult result = new BaseResult();
		UserInfoDAO infoDAO = new UserInfoDAOImplement();
		try {
			// 1.判断用户是否存在
			if (!isUserExisted(connection, infoDAO, uid)) {
				return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.USER_DONT_EXISTED);
			}
			// 2.修改用户年龄
			infoDAO.updateAge(connection, uid, age);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("用户年龄修改成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public BaseResult updatePhone(int uid, String phone) {
		Connection connection = JdbcUtil.getConnection();
		BaseResult result = new BaseResult();
		UserInfoDAO infoDAO = new UserInfoDAOImplement();
		try {
			// 1.判断用户是否存在
			if (!isUserExisted(connection, infoDAO, uid)) {
				return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.USER_DONT_EXISTED);
			}
			// 2.检查手机号码是否为空
			if (phone == null || phone.length() == 0) {
				return setResult(result, RESULT_CODE_FALIED, "手机号码不能为空");
			}
			// 3.修改用户性别
			infoDAO.updatePhone(connection, uid, phone);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("手机号码修改成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public BaseResult updateEmail(int uid, String email) {
		Connection connection = JdbcUtil.getConnection();
		BaseResult result = new BaseResult();
		UserInfoDAO infoDAO = new UserInfoDAOImplement();
		try {
			// 1.判断用户是否存在
			if (!isUserExisted(connection, infoDAO, uid)) {
				return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.USER_DONT_EXISTED);
			}
			// 2.检查邮箱地址是否为空
			if (email == null || email.length() == 0) {
				return setResult(result, RESULT_CODE_FALIED, "邮箱地址不能为空");
			}
			// 3.修改用户性别
			infoDAO.updateEmail(connection, uid, email);
			result.setCode(RESULT_CODE_SUCCESS);
			result.setMsg("邮箱地址修改成功");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return setResult(result, RESULT_CODE_FALIED, ERROR_MSG.DATABASE_ERROR);
		}
	}

	public boolean isUserExisted(Connection connection, UserInfoDAO dao, int uid) throws SQLException {
		int id = dao.queryUserId(connection, uid);
		if (id != 0) {
			return true;
		} else {
			return false;
		}
	}
}
