package com.xiao.blog.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangmx
 * @create 2019-12-01 10:07
 * @Desc
 */

@Data
@AllArgsConstructor
public class Relation {

    private int aId;

    private int bId;

}
