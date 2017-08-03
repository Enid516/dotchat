package com.dotchat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONObject;

/**
 * 所有HttpServlet的基类
 * 在这里将data中的请求数据json string类型处理并转换为JSONObject类型
 * @author Enid
 *
 */
public class BaseServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JSONObject requestJson;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestData = req.getParameter("data");
		req.getParameterValues("data");
		System.out.println("==========================requestData = " + requestData);
//		requestJson =JSON.parseObject(URLDecoder.decode(requestData,"utf-8"));
		super.service(req, res);
	}
}
