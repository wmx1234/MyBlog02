package com.xiao.blog.service;

import com.xiao.blog.model.User;
import com.xiao.blog.vo.LoginUser;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:34
 * @Desc
 */
public interface UserService {

    public User getUserById(Integer id);

    public LoginUser getLoginUser(String userName);

    public List<User> getAll();

    Integer update(User user);
}
