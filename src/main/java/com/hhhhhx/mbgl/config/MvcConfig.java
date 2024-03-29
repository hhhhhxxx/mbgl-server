package com.hhhhhx.mbgl.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                // .addResourceLocations("classpath:/static/img/");
                .addResourceLocations("file:D:\\Code\\小程序\\mbgl-miniapp\\mbgl-server\\src\\main\\resources\\static\\img\\");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    //     FastJsonConfig config = new FastJsonConfig();
    //     config.setSerializerFeatures(
    //             // 保留 Map 空的字段
    //             SerializerFeature.WriteMapNullValue,
    //             // 将 String 类型的 null 转成""
    //             SerializerFeature.WriteNullStringAsEmpty,
    //             // 将 Number 类型的 null 转成 0
    //             SerializerFeature.WriteNullNumberAsZero,
    //             // 将 List 类型的 null 转成 []
    //             SerializerFeature.WriteNullListAsEmpty,
    //             // 将 Boolean 类型的 null 转成 false
    //             SerializerFeature.WriteNullBooleanAsFalse,
    //             // 避免循环引用
    //             SerializerFeature.DisableCircularReferenceDetect);
    //     converter.setFastJsonConfig(config);
    //     converter.setDefaultCharset(StandardCharsets.UTF_8);
    //     List<MediaType> mediaTypeList = new ArrayList<>();
    //     // 解决中文乱码问题，相当于在 Controller 上的 @RequestMapping 中加了个属性 produces = "application/json"
    //     mediaTypeList.add(MediaType.APPLICATION_JSON);
    //     converter.setSupportedMediaTypes(mediaTypeList);
    //     converters.add(converter);
    // }
}
