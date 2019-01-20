package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:59 2019/1/20
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求前pre或者后post执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器里执行的操作  return 任何object的值都表示继续执行
     * setsendzullRepose(false)表示不再继续执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台过滤器了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }

        if (request.getRequestURI().indexOf("login") > 0) {
            return null;
        }


        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (StringUtils.equals(roles, "admin")) {
                        currentContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);
                }
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足！");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
