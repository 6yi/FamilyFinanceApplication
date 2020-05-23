package com.lzheng.familyfinance.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Configuration
 * @Author 6yi
 * @Date 2020/5/23 12:47
 * @Version 1.0
 * @Description:
 */

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Digester digester(){
        return new Digester(DigestAlgorithm.MD5);
    }


    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TokenFilter());
        //添加需要拦截的url
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/use");
        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
        return registrationBean;
    }

}