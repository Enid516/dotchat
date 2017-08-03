package com.dotchat.service;


import com.dotchat.bean.BaseResult;

public class BaseService {
	protected static int RESULT_CODE_SUCCESS = 1000;
	protected static int RESULT_CODE_FALIED = 1001;


	public static class ERROR_MSG{
		public static String USER_DONT_EXISTED = "用户不存在";
		public static String DATABASE_ERROR = "数据库异常";
	}

	protected BaseResult setResult(BaseResult result, int code, String msg){
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
