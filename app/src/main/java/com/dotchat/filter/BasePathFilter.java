package com.dotchat.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 */
public class BasePathFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String prjPath = request.getScheme() + "://" + request.getServerName() +  ":" + request.getServerPort() +
                request.getContextPath();
        request.setAttribute("basePath",prjPath);
        Logger.getLogger("BasePathFilter").info("basePath: " + prjPath);
        filterChain.doFilter(servletRequest,servletResponse);
    }


    public void destroy() {

    }
}
