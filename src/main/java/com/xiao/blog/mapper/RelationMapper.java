package com.xiao.blog.mapper;

import com.xiao.blog.pojo.Relation;
import com.xiao.blog.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-17 16:06
 * @Desc
 */
@Mapper
public interface RelationMapper {

    @Select("select nextval('tbl_fs')")
    public Integer nextValue();

    @Delete("delete from sys_role_permission where permission_id = #{id}")
    public void deleteRelationByPermissionId(Integer id);

    @Select("select role_id from sys_user_role where user_id = #{id}")
    public Integer getRoleByUser(Integer id);

    @Select("select role.* from sys_role_permission srp left join sys_role role on srp.role_id = role.id where srp.permission_id = #{id}")
    public List<Role> getRolesByPermission(Integer id);

    @Delete("delete from sys_role_permission where role_id = #{id}")
    void deleteRelationByRoleId(Integer id);

    /**
     * 批量插入博客和标签对照表
     * @param list
     * @return
     */
    @Insert("<script> " +
            "insert into blog_article_tags (article_id,tags_id) " +
            "values <foreach collection =\"list\" item=\"relation\" index= \"index\" separator =\",\"> " +
            "(#{relation.aId,jdbcType=INTEGER},#{relation.bId,jdbcType=INTEGER})" +
            "</foreach></script>")
    int batchInsertArticleTagsRelation(List<Relation> list);

    /**
     * 删除博客和标签对照表
     * @param id
     */
    @Delete("delete from blog_article_tags where article_id = #{id}")
    void deleteRelationByArticleId(Integer id);


}
