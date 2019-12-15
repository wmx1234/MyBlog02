package com.xiao.blog.exception.assertion;


import com.xiao.blog.exception.BaseException;
import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.model.User;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>业务异常断言</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public class BusinessExceptionAssert{

    /**
     * 本博客是情侣博客，只能有两个博主
     * @param users
     */
    public static void assertBlogerNum(List<User> users){
        if(users.size() > 2){
            throw new BusinessException("本博客是情侣博客，搞那么多博主干毛");
        }
        if(users.size() < 2){
            throw new BusinessException("本博客不适合单身狗，请找到情侣再来");
        }
    }


}
