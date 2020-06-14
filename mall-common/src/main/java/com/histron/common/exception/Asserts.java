package com.histron.common.exception;

import com.histron.common.api.IErrorCode;

/**
 * @author: ycz
 * @create: 2020-05-19 16:36
 * @description:    断言处理类，用于抛出各种API异常
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
