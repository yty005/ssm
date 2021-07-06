package com.yty.ssm.utils;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    Jedis jedis = null;
    /** 检查不通过时，转发的URL */
    private  String forwardUrl;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.jedis = new Jedis("127.0.0.1",6379);
        this.forwardUrl = "/failer.jsp";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //从redis获取验证码
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String phone = servletRequest.getParameter("phone");
        String code = servletRequest.getParameter("code");
        //验证码key
        String codeKey = "VerifyCode"+phone+":code";
        String redisCode = jedis.get(codeKey);
        //判断
        if(redisCode.equals(code)) {
            System.out.println("成功");
        }else {
            System.out.println("失败");
            response.sendRedirect(request.getContextPath() + forwardUrl);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        jedis.close();
    }
}
