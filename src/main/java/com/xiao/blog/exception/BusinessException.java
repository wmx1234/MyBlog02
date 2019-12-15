package com.xiao.blog.exception;

/**
 * @author wangmx
 * @create 2019-12-14 21:54
 * @Desc
 */
public class BusinessException  extends BaseException {

    public BusinessException(){}

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable cause){
        super(message,cause);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }


}
