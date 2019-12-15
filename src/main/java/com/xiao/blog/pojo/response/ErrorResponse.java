package com.xiao.blog.pojo.response;

/**
 * <p>错误返回结果</p>
 *
 * @author wmx
 * @date 2019/12/15
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        super(code, message);
    }
}
