package com.example.springdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.springdemo.model.HelloModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 로그인 상태 체크
public class HelloInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        HelloModel loginData = (HelloModel)session.getAttribute("loginUser");
        if(loginData == null) {
            //response.sendRedirect("/home");
            response.sendError(400, "error");
        }
        return true;
    }
}

