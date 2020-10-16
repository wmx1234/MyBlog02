package com.xiao.blog.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import com.xiao.blog.vo.LoginUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiao.blog.shiro.factory.ShiroUtil;


public class ShiroDbRealm extends AuthorizingRealm {
    
    @Autowired
    private ShiroUtil shiroUtil;

    /**
     *  *登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authcToken;
        //获取用户名
        String userName = (String)usernamePasswordToken.getPrincipal();
        //根据用户名在数据库查询用户（shiroUtil是工具类，实际使用UserMapper访问数据库）
        LoginUser user = shiroUtil.getUser(userName);
        //认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),super.getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
       
        return authenticationInfo;
    }

    /**
     * *权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LoginUser user = (LoginUser)principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<String>();
        roles.add(user.getRole().getRoleName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        
        return info;
    }

    
}
