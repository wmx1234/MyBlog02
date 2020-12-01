package com.xiao.blog.shiro.factory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义过滤器，当拥有权限中的任一角色时，都可以访问该url
 * @author Administrator
 *
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {  
    
    @Override  
    protected boolean isAccessAllowed(ServletRequest req,ServletResponse resp, Object mappedValue) throws Exception {  
        Subject subject = getSubject(req, resp);  
        String[] rolesArray = (String[]) mappedValue;
        //没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;  
        }  
        for (int i = 0; i < rolesArray.length; i++) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(rolesArray[i])) {
                return true;  
            }  
        }  
        return false;  
    }  
}  