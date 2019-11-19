package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.mapper.RelationMapper;
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
    @Autowired
    RelationMapper relationMapper;
    @Override
    public List<Permission> getPermissionsByRole(int roleId) {

        return permissionMapper.getPermissionsByRole(roleId);
    }

    @Override
    public List<Permission> getAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int insert(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public void delete(Integer id) {
        //删除权限
        permissionMapper.deleteByPrimaryKey(id);
        //同时删除角色权限关联
        relationMapper.deleteRelationByPermissionId(id);
    }

    @Override
    public int update(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }
}
