package com.gao.hr.gateway;

import com.gao.hr.common.MyException;
import com.gao.hr.common.MyIllegalAccessException;
import com.gao.hr.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:Gao
 * @Date:2020-06-07 20:17
 */
@Component
public class Authorization implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        final String authHeader = request.getHeader("Admin-Token");
        try {
            if (!StringUtils.isEmpty(authHeader)) {
//                System.out.println(authHeader);
                Claims claims = jwtUtil.parseJWT(authHeader);
                if (!"adminG".equals(claims.get("roles"))) {
                    throw new MyException(50008,"未经授权的访问");
                }
            } else {
                throw new MyException(50008,"未经授权的访问");
            }
        } catch (Exception e) {
            throw new MyException(50008,"未经授权的访问");
        }
        return true;
    }
}
