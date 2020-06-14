package com.histron.common.api;

/**
 * @author: ycz
 * @create: 2020-05-19 16:12
 * @description: 枚举了一些常用API操作码
 **/
public enum  ResultCode implements IErrorCode {
    SUCCESS(200, "ok"),
    FAILED(500, "error"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    MOBILEORPASSWORDERROR(4001,"用户名或密码错误");


    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }}
