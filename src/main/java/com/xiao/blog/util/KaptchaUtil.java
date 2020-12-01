/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiao.blog.util;

import com.xiao.blog.config.BlogProperties;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.shiro.exception.VerCodeEmptyException;
import com.xiao.blog.shiro.exception.VerCodeErrorException;
import com.xiao.blog.vo.LoginUser;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    @Resource
    BlogProperties blogProperties;

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {

        return SpringContextHolder.getBean(BlogProperties.class).getKaptchaOpen();
    }

    /**
     * 校验验证码
     * @param user
     */
    public static void verifyVerCode(LoginUser user){
        //获取验证码
        if(KaptchaUtil.getKaptchaOnOff()){
            String verCode = user.getVerCode();
            if(StringUtils.isEmpty(verCode)){
                throw new VerCodeEmptyException();
            }
            if(!verCode.equals(ShiroKit.getSessionAttr("rightCode"))){
                throw new VerCodeErrorException();
            }
        }
    }

}