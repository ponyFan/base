package com.base.common.sercurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:11
 * @description 角色实体
 */
@Data
@TableName("t_role")
public class  Role {

    private Integer id;

    private String roleName;

    private Long updateTime;

    private Integer deleted;
}
