package com.xiao.blog.vo;

import com.xiao.blog.model.Role;
import com.xiao.blog.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser extends User {

    /**
     * 用户角色
     */
    private RoleVO role;

    /**
     * 登陆验证码
     */
    private String verCode;

    /**
     * 记住我
     */
    private Boolean rememberMe;

    /**
     * 登录失败次数
     */
    private Integer retryCount;

}
