package com.xiao.blog.mapper;

import com.xiao.blog.model.User;
import com.xiao.blog.vo.LoginUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名获取登录用户信息
     * @param userName
     * @return
     */
    LoginUser getLoginUser(String userName);


}