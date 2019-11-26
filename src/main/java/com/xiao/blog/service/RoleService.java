package com.xiao.blog.service;

import com.xiao.blog.model.Role;
import com.xiao.blog.model.TreeModel;

import java.util.List;

/**
 * @author wangmx
 * @date 2019-11-19 10:41
 * @desc:
 */
public interface RoleService {

    public List<Role> getAll();

    Integer getRoleByUser(Integer userId);

    void setPermission(List<Integer> permissions, Integer roleId);
}
