package com.histron.mall.controller;

import com.histron.common.api.CommonResult;
import com.histron.common.api.IErrorCode;
import com.histron.common.api.ResultCode;
import com.histron.common.controller.BaseController;
import com.histron.mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author: ycz
 * @date: 2020/06/09
 **/
//解决跨域
@CrossOrigin
@RestController
@Api(tags = "userController", description = "用户管理")
@RequestMapping(value = "/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @RequiresPermissions("user-home")
    public CommonResult home(){
        return CommonResult.success("欢迎访问个人主页");
    }
    /**
     * 用户登录
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public CommonResult login(@RequestBody Map<String,Object> loginMap){
        String mobile = (String) loginMap.get("mobile");
        String password = (String) loginMap.get("password");
        try {
            //构造登录令牌
            password = new Md5Hash(password , mobile , 3).toString();
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile , password);
            //获取subject
            Subject subject = SecurityUtils.getSubject();
            //调用login方法,进入realm完成认证
            subject.login(upToken);
            //获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //构造返回结果
            return  CommonResult.success(sessionId);
        }catch (Exception e){
            return CommonResult.failed(ResultCode.MOBILEORPASSWORDERROR);
        }
    }


}
