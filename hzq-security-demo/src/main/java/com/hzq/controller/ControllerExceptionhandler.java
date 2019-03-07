package com.hzq.controller;

import com.hzq.exception.UserNotException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理 Controller 里 抛出的异常
 */
@ControllerAdvice
public class ControllerExceptionhandler {

    @ExceptionHandler(UserNotException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerNotExistException(UserNotException ex){
        Map<String,Object> map=new HashMap<>();
        map.put("id",ex.getId());
        map.put("message",ex.getMessage());
        return map;
    }
}
