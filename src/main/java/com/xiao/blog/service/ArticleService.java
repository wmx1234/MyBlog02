package com.xiao.blog.service;

import cn.hutool.json.JSONObject;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArticleVO;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 21:01
 * @Desc
 */
public interface ArticleService {

    public void save(Params params);

    public List<ArticleVO> getArticleByUserId(int userId);

    public List archive(Integer userId);

    public List classify(Integer userId);
}
