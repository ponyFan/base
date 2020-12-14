package com.base.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/8/3 16:35
 * @description
 */
@Data
public class MeunInfo {

    //菜单id
    private int id;
    //菜单标题
    private String meunTitle;
    //菜单地址
    private String meunUrl;
    //菜单状态
    private int meunStatus;
    //菜单标识
    private int meunParent;
    //菜单排序
    private int meunSort;
    //子菜单集合
    private List<MeunInfo> childrenList;
}
