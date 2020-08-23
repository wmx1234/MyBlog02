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

    /**
     * 文章数（权重）
     * 标签云中的比例大小
     */
    private Integer weight;

    /**
     * 标签云显示名称
     */
    private String text;

    /**
     * 标签连接
     */
    private String link;

    /**
     * 标签颜色
     */
    private String color;
}
