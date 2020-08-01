package com.base.common.sercurity;

import com.base.common.sercurity.entity.Menu;
import com.base.common.sercurity.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:21
 * @description 自定义用户即角色实体属性
 */
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = 6188881703720456397L;

    private Long id;// 用户id
    private String username;// 用户名
    private String password;// 密码
    private Role userRole;// 用户权限集合
    private List<Menu> roleMenus;// 角色菜单集合
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity() {

    }

    public UserEntity(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities,
                      List<Menu> roleMenus, Role userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roleMenus = roleMenus;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public List<Menu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<Menu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
