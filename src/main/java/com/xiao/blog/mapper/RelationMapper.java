package com.xiao.blog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangmx
 * @create 2019-11-17 16:06
 * @Desc
 */
@Mapper
public interface RelationMapper {

    @Delete("delete from sys_role_permission where permission_id = #{id}")
    public void deleteRelationByPermissionId(Integer id);
}
