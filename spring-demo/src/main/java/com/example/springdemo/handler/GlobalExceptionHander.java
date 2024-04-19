package com.example.springdemo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.dto.ResponseDto;


// 모든 오류 그냥 여기서 보냄
@ControllerAdvice
@RestController
public class GlobalExceptionHander {

    //@ExceptionHandler(value = IllegalArgumentException.class)
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<?> handleArgumentException(Exception e) {
        
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
