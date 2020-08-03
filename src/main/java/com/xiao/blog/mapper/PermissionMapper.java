package com.xiao.blog.mapper;

import com.xiao.blog.model.Permission;
import com.xiao.blog.pojo.TreeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);


    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();


    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionsByRole(int roleId);

    List<Permission> getPermissionsByParent(Integer parentId);

    List<TreeModel> getPermissionTree(Integer roleId,Integer parentId);

    List<Permission> getMenu(Integer roleId);
}