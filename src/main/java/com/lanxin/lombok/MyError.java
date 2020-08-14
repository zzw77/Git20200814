package com.lanxin.lombok;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//该注解定义全局异常处理类
@ResponseBody
public class MyError {
    @ExceptionHandler(value = UnauthorizedException.class) //该注解声明异常处理方法
    public MyResult exceptionHandler(Exception e) {

        return MyResult.fail(10000,"你的权限不足");
    }

}
