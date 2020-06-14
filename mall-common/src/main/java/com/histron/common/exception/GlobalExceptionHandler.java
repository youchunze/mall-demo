package com.histron.common.exception;

import com.histron.common.api.CommonResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ycz
 * @create: 2020-05-19 16:37
 * @description:    全局异常处理
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e){
        if(e.getErrorCode() != null){
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }


    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public CommonResult error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return CommonResult.failed("权限不足");
    }
}
