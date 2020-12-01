package com.xiao.blog.shiro.exception;


import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常
 */
public class VerCodeErrorException extends AuthenticationException {

    public VerCodeErrorException(){
        super("验证码错误！！！");
    }

}
