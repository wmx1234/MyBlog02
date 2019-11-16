package com.xiao.blog.shiro.factory;

import java.util.List;
import java.util.Map;


import com.xiao.blog.model.User;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * account 账号
     */
    User getUser(String userName);


    /**
     * 获取角色权限列表
     *
     * @param roleId 角色id
     */
    List<String> findPermissionsByRoleId(Integer roleId);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(Integer roleId);

    /**
     * 获取shiro的认证信息
     */
    //SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);


    Map<String, String>  loadFilterChainDefinitions();
}
