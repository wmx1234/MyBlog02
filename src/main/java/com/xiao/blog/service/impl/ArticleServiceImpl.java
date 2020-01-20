package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.Relation;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.ArticleUtil;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangmx
 * @create 2019-11-30 21:02
 * @Desc
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public void save(Params params) {

        Article article = params.getObject("article",Article.class);

        int isExist = articleMapper.articleIsExist(article.getId());

        if(isExist <= 0) insert(params);

        else update(params);

    }

    @Override
    public List<ArticleVO> getArticleByUserId(int userId) {

        return articleMapper.getArticles(new Article(userId));

    }

    @Override
    public JSONObject archive(Integer userId) {

        List<ArticleVO> articles = getArticleByUserId(userId);

        //年份标识，用于前台取出数据
        Set<String> flag = new HashSet<String>();

        Map<String,Map<String,List<ArticleVO>>> result = new HashMap<String,Map<String,List<ArticleVO>>>();

        for(ArticleVO article:articles){

            String createDate = article.getCreateDate();

            String year = createDate.substring(0,4);

            if(result.get(year) != null){
                Map<String,List<ArticleVO>> archive = result.get(year);
                if(archive.get(createDate) != null){
                    archive.get(createDate).add(article);
                }else{
                    List<ArticleVO> list = new ArrayList<ArticleVO>();
                    list.add(article);
                    archive.put(createDate,list);
                }

            }else{
                Map<String,List<ArticleVO>> archive = new HashMap<String,List<ArticleVO>>();
                List<ArticleVO> list = new ArrayList<ArticleVO>();
                list.add(article);
                archive.put(createDate,list);
                result.put(year,archive);

            }
        }

        return JSONUtil.parseFromMap(result);
    }


    private void insert(Params params){

        Article article = params.getObject("article",Article.class);

        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));

        article.setCreateDate(DateUtil.today());

        article.setUserId(ShiroKit.getUser().getId());

        article.setLastArticleId(articleMapper.getLastArticleId());

        articleMapper.insert(article);

        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }

    private void update(Params params){

        Article article = params.getObject("article",Article.class);

        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));

        article.setUpdateDate(DateUtil.today());

        articleMapper.updateArticleById(article);

        relationMapper.deleteLabelsByArticleId(article.getId());

        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        //relationMapper.deleteCategoriesByArticleId(article.getId());

        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }
}
