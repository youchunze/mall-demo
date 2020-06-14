package com.histron.common.exception;

import com.histron.common.api.IErrorCode;

/**
 * @author: ycz
 * @create: 2020-05-19 16:32
 * @description: 自定义API异常类
 **/
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode =errorCode;
    }

    public ApiException(String message){
        super(message);
    }

    public ApiException(Throwable throwable){
        super(throwable);
    }

    public ApiException(String message,Throwable throwable){
        super(message,throwable
        );
    }

    public IErrorCode getErrorCode(){
        return errorCode;
    }
}
