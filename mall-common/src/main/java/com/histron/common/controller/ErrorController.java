package com.histron.common.controller;

import com.histron.common.api.CommonResult;
import com.histron.common.api.IErrorCode;
import com.histron.common.api.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hyl
 * @date: 2020/02/08
 **/
@RestController
@CrossOrigin
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value = "/autherror")
    public CommonResult autherror(int code){
        return code == 1 ? CommonResult.failed(ResultCode.UNAUTHORIZED) : CommonResult.failed(ResultCode.FORBIDDEN);
    }
}
