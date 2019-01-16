package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 0:19 2019/1/17
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论如何都放行。具体能不能操作还是在具体的操作中判断。
        //拦截器只是负责把请求头中包含token的令牌进行一个解析验证。
        String header = request.getHeader("Authorization");
        //header不为空
        if (!StringUtils.isEmpty(header)) {
            //以Bearer 开头
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null && "admin".equals(roles)) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && "user".equals(roles)) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确！");
                }
            }
        }
        return true;
    }

}
