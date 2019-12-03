package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.TreeModel;
import com.xiao.blog.service.PermissionService;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.util.ToolUtil;
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
    public List<Permission> getPermissionsByParent(Integer parentId) {
        return permissionMapper.getPermissionsByParent(parentId);
    }

    @Override
    public int insert(Permission permission) {
        permission.setId(DataBaseUtil.nextValue());
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

    @Override
    public int deleteBatch(List<Integer> ids) {
        System.out.println(ids.size());
        return permissionMapper.deleteBatch(ids);
    }

    @Override
    public List<TreeModel> getPermissionTree() {
        return permissionMapper.getPermissionTree();
    }
}
