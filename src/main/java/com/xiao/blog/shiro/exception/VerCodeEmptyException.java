package com.xiao.blog.shiro.exception;


import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码为空异常
 */
public class VerCodeEmptyException extends AuthenticationException {

    public VerCodeEmptyException(){
        super("验证码不能为空！！！");
    }

}
