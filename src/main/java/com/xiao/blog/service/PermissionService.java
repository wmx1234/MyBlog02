package com.xiao.blog.service;

import com.xiao.blog.model.Permission;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:38
 * @Desc
 */
public interface PermissionService {

    List<Permission> getPermissionsByRole(int roleId);

}
