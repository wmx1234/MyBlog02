package com.xiao.blog.shiro.factory;

import com.sun.deploy.util.StringUtils;
import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.mapper.UserMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.Role;
import com.xiao.blog.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Shiro工具类
 * @author  wangmx
 */
@Component
@Transactional(readOnly = true)
public class ShiroUtil implements IShiro {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public LoginUser getUser(String userName) {
        return userMapper.getLoginUser(userName);
    }

    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        List<Permission> permissions = permissionMapper.selectAll();
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        for(Permission permission:permissions) {
            List<Role> roles =  permission.getRoles();
            if(roles == null || roles.size() == 0) {
                filterChainDefinitionMap.put(permission.getUrl(),"anon");
            }else {
                Set<String> roleNameContainer = new HashSet<String>();
                for(Role role:roles) {
                    roleNameContainer.add(role.getRoleName());
                }
                filterChainDefinitionMap.put(permission.getUrl(),"customRolesAuthorizationFilter["+StringUtils.join(roleNameContainer,",")+"]");
            }
        }

        filterChainDefinitionMap.put("/**", "customRolesAuthorizationFilter");
        filterChainDefinitionMap.put("/admin/logout", "logout");
        return filterChainDefinitionMap;
    }
    
    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return null;
    }

    
    
}
