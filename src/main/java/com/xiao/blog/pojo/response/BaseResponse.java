package com.xiao.blog.pojo.response;


import com.xiao.blog.constants.ResponseCode;
import lombok.Data;

/**
 * <p>基础返回结果</p>
 * @author wmx
 * @date 2019/12/15
 */
@Data
public class BaseResponse {

    /**
     * 返回码
     */
    protected int code;

    /**
     * 返回消息
     */
    protected String msg;



    public BaseResponse() {
        // 默认创建成功的回应
        this(ResponseCode.SUCCESS,"操作成功");
    }

    public BaseResponse(String msg) {
        // 默认创建成功的回应
        this(ResponseCode.SUCCESS,msg);
    }


    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
