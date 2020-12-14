package com.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.common.basebean.RestfulTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zelei.fan
 * @date 2020/9/5 14:21
 * @description
 */
@RestController
@RequestMapping("/game")
@Api
public class GameDownloadController {

    @Autowired
    RestfulTemplate restfulTemplate;

    @RequestMapping("/download")
    @ApiOperation("dddd")
    public void downloadUrl(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", null);
        Object call = restfulTemplate.call("http://api.51gamedd.com/fname?token=LfC1WytJLIJY", HttpMethod.GET, jsonObject, JSONObject.class);
        System.out.println(call);
    }
}
