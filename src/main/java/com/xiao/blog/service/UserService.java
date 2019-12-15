package com.xiao.blog.service;

import com.xiao.blog.model.User;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:34
 * @Desc
 */
public interface UserService {

    public User getLoginUser(String userName);

    public List<User> getAll();

    List<User> getBloger();
}
