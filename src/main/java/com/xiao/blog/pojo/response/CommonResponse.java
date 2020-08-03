package com.xiao.blog.pojo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>通用返回结果</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {
    /**
     * 数据列表
     */
    protected T data;

    public CommonResponse() {
        super();
    }

    public CommonResponse(Integer code,T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public CommonResponse(T data) {
        super();
        this.data = data;
    }
}
