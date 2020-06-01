package com.lzheng.familyfinance.config;

/**
 * @ClassName CorsFilter
 * @Author 6yi
 * @Date 2020/5/25 10:14
 * @Version 1.0
 * @Description:
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, OPTIONS, DELETE,PUT");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type, x-requested-with, X-Custom-Header, Authorization");
        }
        chain.doFilter(request, response);
    }

}