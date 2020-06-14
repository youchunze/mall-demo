package com.histron.mall.shiro;

import com.histron.mall.service.PermissionService;
import com.histron.mall.service.UserService;
import com.histron.model.entity.Permission;
import com.histron.model.entity.Role;
import com.histron.model.entity.User;
import com.histron.model.entity.response.ProfileResult;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author: ycz
 * @date: 2020/06/08
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    //认证方法  
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {

        //获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //根据手机号查询用户
        User user = userService.findByMobile(mobile);
        //根据用户是否存在,用户密码是否和输入密码一致
        if(user != null && user.getPassword().equals(password)){
            Map map = new HashMap();
            ProfileResult result = null;
            result = new ProfileResult(user);
//            List<Permission> list = permissionService.findAll(map);
//            result = new ProfileResult(user, list);
            //构造方法：安全数据,密码,realm域名
            SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(result,password,this.getName());
            return info;
        }
        //返回null,会抛出异常,表示用户名和密码不匹配
        return null;
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
//        User user = (User)principalCollection.getPrimaryPrincipal();
        //获取权限信息
        Set<String> apisPerms = (Set<String>) result.getRoles().get("apis");
//        Set<String> roles = new HashSet<>();
//        Set<String> permissions = new HashSet<>();
//        for (Role role : user.getRoles()){
//            roles.add(role.getName());
//            for (Permission perm : role.getPermissions()){
//                permissions.add(perm.getCode());
//            }
//        }
        //构造权限信息,返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(apisPerms);
//        info.setRoles(roles);
//        info.setStringPermissions(permissions);
        return info;

    }
}
