package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.mapper.RoleMapper;
import com.xiao.blog.model.Role;
import com.xiao.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
