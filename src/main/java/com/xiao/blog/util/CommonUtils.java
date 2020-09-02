package com.xiao.blog.util;

import com.xiao.blog.vo.ArchiveVO;
import com.xiao.blog.vo.ArticleVO;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @author wmx
 * @version 1.0
 * @description
 * @date 2020-08-22
 */
public class CommonUtils {

    /**
     * 随机颜色
     *
     * @param len
     * @return
     */
    public static String randomHexStr(int len) {
        try {
            StringBuffer result = new StringBuffer("#");
            for (int i = 0; i < len; i++) {
                //随机生成0-15的数值并转换成16进制
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println("获取16进制字符串异常，返回默认...");
            return "#00CCCC";
        }
    }


}
