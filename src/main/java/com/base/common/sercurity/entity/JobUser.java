package com.base.common.sercurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/7/9 10:01
 * @description
 */
@Data
public class JobUser {

    private Long id;// 用户id

    private String username;// 用户名

    private String password;// 密码

    private Role userRole;// 用户权限集合

    private List<Menu> roleMenus;// 角色菜单集合

}
