package com.example.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springdemo.interceptor.HelloInterceptor;

// 로그인 확인
@Configuration
public class HelloConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HelloInterceptor())
        .addPathPatterns("/QQQ/hello/**")
        .excludePathPatterns("/QQQ/login");
    }
}
