package com.xiao.blog.shiro.factory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xiao.blog.mapper.UserMapper;
import com.xiao.blog.model.User;


@Component
@Transactional(readOnly = true)
public class ShiroUtil implements IShiro {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User getUser(String userName) {
        
        return userMapper.getUser(userName);
    }

    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        List<Permission> permissions = permissionMapper.selectAll();
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        for(Permission permission:permissions) {

            List<Role> roles = permission.getRoles();
            if(roles == null || roles.size() == 0) {
                filterChainDefinitionMap.put(permission.getUrl(),"anon");
            }else {
                String roleNames = "";
                for(Role role:roles) {
                    roleNames += role.getRoleName()+",";
                }
                filterChainDefinitionMap.put(permission.getUrl(),"customRolesAuthorizationFilter["+roleNames.substring(0, roleNames.length()-1)+"]");
            }
        }

        filterChainDefinitionMap.put("/**", "customRolesAuthorizationFilter");
        filterChainDefinitionMap.put("/logout", "logout");
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
