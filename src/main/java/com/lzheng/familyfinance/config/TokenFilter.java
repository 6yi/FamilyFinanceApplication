package com.lzheng.familyfinance.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName TokenFilter
 * @Author 6yi
 * @Date 2020/5/23 14:40
 * @Version 1.0
 * @Description:
 */


public class TokenFilter  extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req
            , ServletResponse res
            , FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            if (authHeader == null || !authHeader.startsWith("bearer;")) {
//                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }
            final String token = authHeader.substring(7);

            try {
//                if (audience == null) {
//                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//                    audience = (Audience) factory.getBean("audience");
//                }
//                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
//                if (claims == null) {
//                    throw new LoginException(ResultEnum.LOGIN_ERROR);
//                }
//
//                request.setAttribute(Constants.CLAIMS, claims);
            } catch (final Exception e) {
//                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }
            chain.doFilter(req, res);
        }
    }
}
