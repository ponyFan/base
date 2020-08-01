package com.base.common.sercurity;

import com.base.common.sercurity.dao.MenuDao;
import com.base.common.sercurity.dao.RoleDao;
import com.base.common.sercurity.dao.UserDao;
import com.base.common.sercurity.entity.Menu;
import com.base.common.sercurity.entity.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:30
 * @description 自定义实现 UserDetailsService
 */
@Service
@Log4j2
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户
        UserEntity user = userDao.getUserByUsername(username);
        log.info("current login user :", user);
        if (user != null) {
            //根据用户id获取用户角色
            Role role = roleDao.getUserRoleByUserId(user.getId());
            // 填充权限
            Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            //填充权限菜单
            List<Menu> menus = new ArrayList<>();
            if (Objects.nonNull(role)){
                menus = menuDao.getRoleMenuByRoles(role.getId());
            }
            return new UserEntity(user.getId(), username, user.getPassword(), authorities, menus, role);
        } else {
            log.error(username +" not found");
            throw new UsernameNotFoundException(username +" not found");
        }
    }
}
