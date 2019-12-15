package com.xiao.blog.exception;


import lombok.Getter;

/**
 * <p>基础异常类，所有自定义异常类都需要继承本类</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
@Getter
public class BaseException extends RuntimeException {



    public BaseException(){}
    public BaseException(String message){
        super(message);
    }
    public BaseException(String message, Throwable cause){
        super(message,cause);
    }
    public BaseException(Throwable cause){
        super(cause);
    }

}
