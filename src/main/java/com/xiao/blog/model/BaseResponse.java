package com.xiao.blog.model;

import lombok.Data;

/**
 * @author wangmx
 * @create 2019-12-02 21:12
 * @Desc
 */
@Data
public class BaseResponse<T> {

    private int code;

    private String message;

    private T data;

}
