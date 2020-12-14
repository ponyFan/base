package com.base.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/12/10 11:50
 * @description
 */
@Data
public class MenuModel {

    private Integer id;

    private Integer pid;

    private String menuName;

    private String menuUrl;

    private Integer menuType;

    private Integer sequence;

    private Integer deleted;

    private Long updateTime;

    private List<MenuModel> child;
}
