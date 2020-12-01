package com.xiao.blog.vo;

import com.xiao.blog.model.BoxCategories;
import lombok.Data;

import java.util.List;

@Data
public class BoxCategoriesVO extends BoxCategories {

    private List<BoxCategoriesVO> children;
}
