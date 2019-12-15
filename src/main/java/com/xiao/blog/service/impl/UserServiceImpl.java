package com.xiao.blog.service.impl;

import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.exception.assertion.BusinessExceptionAssert;
import com.xiao.blog.mapper.UserMapper;
import com.xiao.blog.model.User;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-15 21:34
 * @Desc
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getLoginUser(String userName) {
        return userMapper.getLoginUser(userName);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getBloger() throws BusinessException {

        List<User> users = userMapper.getBloger();


        return users;
    }
}
