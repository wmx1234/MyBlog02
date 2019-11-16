package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:41
 * @Desc
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionsByRole(int roleId) {

        return permissionMapper.getPermissionsByRole(roleId);
    }
}
