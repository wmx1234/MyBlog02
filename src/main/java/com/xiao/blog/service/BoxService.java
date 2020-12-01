package com.xiao.blog.service;

import com.xiao.blog.model.Article;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.BoxCategoriesVO;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-30 21:01
 * @Desc
 */
public interface BoxService {

    public List<BoxCategoriesVO> getBoxCategoriesVO();

}
