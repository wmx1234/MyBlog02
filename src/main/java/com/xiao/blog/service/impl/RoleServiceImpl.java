package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.mapper.RoleMapper;
import com.xiao.blog.model.Role;
import com.xiao.blog.service.RoleService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wangmx
 * @date 2019-11-19 10:42
 * @desc:
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public List<Role> getAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Integer getRoleByUser(Integer userId) {
        return relationMapper.getRoleByUser(userId);
    }

    /**
     * 给角色授权
     * @param permissions
     * @param roleId
     */
    @Override
    public void setPermission(List<Integer> permissions, Integer roleId) {

        //删除之前的角色权限
        relationMapper.deleteRelationByRoleId(roleId);

        List<Map> list = new ArrayList<Map>();

        for(Integer permissionId:permissions){

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("roleId",roleId);
            map.put("permissionId",permissionId);
            list.add(map);

        }
        roleMapper.insertRolePermissionRelation(list);

    }

}
