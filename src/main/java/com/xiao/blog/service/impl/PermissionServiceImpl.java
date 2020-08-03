package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.PermissionMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.pojo.TreeModel;
import com.xiao.blog.service.PermissionService;
import com.xiao.blog.util.DataBaseUtil;
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
    public int delete(Integer id) {

        //同时删除角色权限关联
        relationMapper.deleteRelationByPermissionId(id);

        //删除权限
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }


    @Override
    public List<TreeModel> getPermissionTree(Integer roleId) {
        List<TreeModel> permissionTree = permissionMapper.getPermissionTree(roleId,0);
        for(TreeModel model:permissionTree){
            model.setChildren(permissionMapper.getPermissionTree(roleId,model.getId()));
        }
        return permissionTree;
    }

    @Override
    public List<Permission> getMenu(Integer id) {
        return permissionMapper.getMenu(id);
    }
}
