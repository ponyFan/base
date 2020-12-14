package com.base.util;

import com.base.common.sercurity.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zelei.fan
 * @date 2020/12/10 11:24
 * @description
 */
public class UserUtil {

    /**
     * 获取登录用户信息
     * @return
     */
    public static UserEntity getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (UserEntity) authentication.getPrincipal();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Long a =0L;
        System.out.println(a.equals(0));
    }
}
