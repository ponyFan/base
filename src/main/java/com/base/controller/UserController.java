package com.base.controller;

import com.base.common.sercurity.UserEntity;
import com.base.dao.UserMenuDAO;
import com.base.entity.MenuModel;
import com.base.util.UserUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zelei.fan
 * @date 2020/12/10 11:27
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMenuDAO userMenuDAO;

    @GetMapping("/current")
    public List<MenuModel> menuCurrent() {
        UserEntity loginUser = UserUtil.getLoginUser();
        Long id = Long.valueOf(String.valueOf(loginUser.getId()));
        List<MenuModel> list = Lists.newArrayList();
        userMenuDAO.listByUserId(id).forEach(menu -> {
            MenuModel menuModel = new MenuModel();
            BeanUtils.copyProperties(menu, menuModel);
            list.add(menuModel);
        });
        //找出一级菜单
        List<MenuModel> firstLevel = list.stream().filter(p -> p.getPid().equals(0)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(m -> {
            setChild(m, list);
        });
        return firstLevel;
    }

    /**
     * 设置子元素
     * @param m
     * @param menus
     */
    private void setChild(MenuModel m, List<MenuModel> menus) {
        List<MenuModel> child = menus.parallelStream().filter(a -> a.getPid().equals(m.getId())).collect(Collectors.toList());
        m.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, menus);
            });
        }
    }
}
