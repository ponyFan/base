package com.base.common.sercurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:09
 * @description 用户实体
 */
@Data
@TableName("t_user")
public class User {

    private Integer id;

    private String username;

    private String password;

    private Long updateTime;

    private Integer deleted;
}
