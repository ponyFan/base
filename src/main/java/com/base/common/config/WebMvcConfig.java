package com.base.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zelei.fan
 * @date 2020/7/30 10:37
 * @description 采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态文件路径（可以通过http://ip:port/文件路径访问）
     */
    @Value("${upload.file.path}")
    private String path;

    /**
     * 解决静态资源处理问题
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!path.endsWith("/")){
            path = path + "/";
        }
        /*
        对静态文件做一个虚拟映射，resourcehandler中参数/path/**是虚拟路径，即通过 http://localhost:8102/path/589c2c8d86cff.jpg
        即可访问实际物理地址下的文件
         loacation中参数则是需要指定的静态文件路径，如：file:/C:/Users/11564/Pictures/Camera Roll/  或者  linux：file:/root/fzl
         页面只需<img src="/Path/picName.jpg" />以path开头后面文件名即具体目录下的文件名
         */
        registry.addResourceHandler(path + "**").addResourceLocations("file:" + path);
        /*设置静态文件如html、js、css、jpg等*/
        registry.addResourceHandler("/**").addResourceLocations("/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/META-INF/resources/");
        /*registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");*/
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedMethods("DELETE", "POST", "GET", "PUT")
                .allowedOrigins("*");
    }
}
