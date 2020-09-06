package com.xiao.blog.vo;

import com.xiao.blog.model.Categories;
import lombok.Data;

/**
 * @author wmx
 * @version 1.0
 * @desc
 * @date 2020-02-03 23:29
 */
@Data
public class CategoriesVO extends Categories {

    private Integer articleCount;

    private String color;

    private String link;

}
