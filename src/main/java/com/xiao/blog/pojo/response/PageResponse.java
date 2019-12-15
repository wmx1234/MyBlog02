package com.xiao.blog.pojo.response;

import cn.hutool.core.collection.CollUtil;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页数据
 * @param <T>
 */
@Data
@AllArgsConstructor
public class PageResponse<T> extends BaseResponse{



    //返回数据总条数
    private long count;

    /**
     * 数据列表
     */
    private List<T> data;

    public PageResponse(int count,List<T> data){
        super();
        this.count = count;
        this.data = data;
    }

    public PageResponse(PageInfo info){
        super();
        this.count = info.getTotal();
        this.data = info.getList();
    }
}
