package com.xiao.blog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.pojo.TreeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangmx
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    int deleteByPrimaryKey(Integer id);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionsByRole(int roleId);

    List<Permission> getPermissionsByParent(Integer parentId);

    List<TreeModel> getPermissionTree(Integer roleId,Integer parentId);

    List<Permission> getMenu(Integer roleId);
}