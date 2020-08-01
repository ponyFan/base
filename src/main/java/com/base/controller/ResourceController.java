package com.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:59
 * @description 用于不同权限页面访问测试
 */
@Controller
public class ResourceController {

    @GetMapping("/publicResource")
    public String toPublicResource() {
        return "resource/public";
    }

    @GetMapping("/vipResource")
    public String toVipResource() {
        return "resource/vip";
    }
}
