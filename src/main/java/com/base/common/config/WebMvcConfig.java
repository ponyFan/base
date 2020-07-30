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
        /*设置本地磁盘路径，如path = /root/tmp */
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
