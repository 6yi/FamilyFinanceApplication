package com.lzheng.familyfinance.exception;

import com.lzheng.familyfinance.dto.Result;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Author 6yi
 * @Date 2020/5/24 12:49
 * @Version 1.0
 * @Description:
 */

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class) //该注解声明异常处理方法
    public Result exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        //对于自定义异常的处理
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            Result result = new Result();
            result.setMsg(ex.getMessage());
            result.setCode(ex.getCode());
            return result;
            //对于绑定异常的处理，使用jsr303中的自定义注解抛出的异常属于绑定异常
        }else {
            BindException ex = (BindException)e;
            Result result = new Result();
            result.setMsg(ex.getMessage());
            return result;
        }
    }
}