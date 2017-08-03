package com.dotchat.servlet;

import com.alibaba.fastjson.JSON;
import com.dotchat.bean.BaseResult;
import com.dotchat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends BaseServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService service = new UserService();
        BaseResult result = service.login(username, password);
        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();

        String realPath = getClass().getClassLoader().getResource("/").toString();
        writer.write(jsonString);
        writer.write("\n" + realPath);
        writer.close();


    }
}
