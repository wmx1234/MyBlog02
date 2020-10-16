package com.xiao.blog.vo;

public class PasswordUser {

    /**
     * 用户角色
     */
    private Integer id;

    /**
     * 登陆验证码
     */
    private String oldPassword;

    /**
     * 记住我
     */
    private String newPassword;

    /**
     * 登录失败次数
     */
    private String confirmPassword;

}
