package com.xiao.blog.vo;

import com.xiao.blog.model.Tags;
import lombok.Data;

/**
 * @author wmx
 * @version 1.0
 * @desc 标签VO
 * @date 2020-02-03 21:55
 */
@Data
public class TagsVO extends Tags {

    private Integer articleCount;

}
