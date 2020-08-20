package com.xiao.blog.pojo.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-18 21:11
 * @Desc
 */
@Data
@ToString
public class PageRequest<T> extends BaseRequest<T>{

    @Value("${pagehelper.pageSize}")
    private int limit;

    private int page;

    private T obj;
}
