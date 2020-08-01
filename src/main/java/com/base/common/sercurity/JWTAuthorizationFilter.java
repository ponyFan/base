package com.base.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.base.common.ServiceException;
import com.base.common.sercurity.entity.JobUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by jingwk on 2019/11/17
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public static ThreadLocal<JobUser> threadLocal = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (ServiceException e) {
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSON.toJSONString(R.failed(e.getMsg())));
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new ServiceException(201, "登录时间过长，请退出重新登录");
        }else {
            String username = JwtTokenUtils.getUsername(token);
            String role = JwtTokenUtils.getUserRole(token);
            UserEntity user = new UserEntity();
            user.setId(JwtTokenUtils.getUserId(token));
            user.setUsername(username);
            user.setUserRole(JwtTokenUtils.getRole(token));
            user.setRoleMenus(JwtTokenUtils.getMenus(token));
            System.out.println("*************filter thread is : " + Thread.currentThread());
            /*将用户信息存储到缓存，一边线程能直接读取*/
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(user, null,
                        Collections.singleton(new SimpleGrantedAuthority(role))
                );
            }
        }
        return null;
    }

}
