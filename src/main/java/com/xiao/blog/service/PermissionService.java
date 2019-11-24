package com.xiao.blog.service;

import com.xiao.blog.model.Permission;
import com.xiao.blog.model.PermissionTree;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:38
 * @Desc
 */
public interface PermissionService {

    List<Permission> getPermissionsByRole(int roleId);

    List<Permission> getAll();

    List<Permission> getPermissionsByParent(Integer parentId);

    int insert(Permission permission);

    void delete(Integer id);

    int update(Permission permission);

    int deleteBatch(List<Integer> ids);


    List<PermissionTree> getPermissionTree();
}
