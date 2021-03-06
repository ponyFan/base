package com.base.common.sercurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:12
 * @description 菜单实体
 */
@Data
@TableName("t_menu")
public class Menu {

    private Integer id;

    private Integer pid;

    private String menuName;

    private String menuUrl;

    private Integer menuType;

    private Integer sequence;

    private Integer deleted;

    private Long updateTime;
}
