package com.gao.hr.config;

import com.gao.hr.gateway.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author:Gao
 * @Date:2020-04-18 10:38
 */
@Configuration
public class GatewayConfig implements WebMvcConfigurer {
    @Autowired
    private Authorization authorization;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorization)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/registry", "/user/info", "/file/**", "/employee/importEmployeeInfo/**"
                        , "/webjars/**", "/swagger-resources/**", "/swagger-ui.html/**"
                );
    }
}