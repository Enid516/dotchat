package com.dotchat.servlet;

import com.alibaba.fastjson.JSON;
import com.dotchat.bean.BaseResult;
import com.dotchat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String username = req.getParameter("username");
        UserService service = new UserService();
        BaseResult result = service.logout(username);
        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
