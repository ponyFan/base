package com.base.controller;

import com.base.common.sercurity.UserEntity;
import com.base.common.sercurity.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:57
 * @description
 */
@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;
    /**
     * 初始化用户数据
     */
    @RequestMapping("/initUserData")
    public @ResponseBody
    String initUserData() {
        //普通用户
        UserEntity user=new UserEntity();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        userDao.insertUser(user);
        //管理员
        UserEntity admin=new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userDao.insertUser(admin);

        return "success";
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "用户名或者密码不正确");
        }
        if (logout != null) {
            mav.addObject("msg", "退出成功");
        }
        return mav;
    }
}
