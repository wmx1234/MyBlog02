package com.xiao.blog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.mapper.TagsMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.model.Tags;
import com.xiao.blog.pojo.Relation;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.ArticleUtil;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wangmx
 * @create 2019-11-30 21:02
 * @Desc
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    RelationMapper relationMapper;

    @Resource
    TagsMapper tagsMapper;

    @Override
    public void save(ArticleVO articleVO) {

        if(articleVO.getId() == null){
            //保存博客
            this.insert(articleVO);
        }else{
            //update(params.getObject("article",Article.class));
        }

    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {

    }

    @Override
    public List<ArticleVO> getArticleListByTagsId(Integer id) {

        ArticleVO article = new ArticleVO();

        article.setTagsId(id);

        List<ArticleVO> articleListByField = articleMapper.getArticleListByField(article);

        return articleListByField;

    }

    @Override
    public List<ArticleVO> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    @Override
    public List<ArticleVO> getArticleListByField(ArticleVO article) {
        return articleMapper.getArticleListByField(article);
    }

    @Override
    public ArticleVO getArticleById(int id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public ArticleVO getTopArticle() {
        ArticleVO article = new ArticleVO();
        article.setTop(1);
        List<ArticleVO> articleListByField = articleMapper.getArticleListByField(article);
        if(articleListByField != null && articleListByField.size() > 0){
            return articleListByField.get(0);
        }

        return article;
    }

    @Override
    public List<ArticleVO> archive(){

        List<ArticleVO> allArticles = articleMapper.getAllArticles();

        Set<String> yearSet = new HashSet<String>();

        Set<String> yearMonthSet = new HashSet<String>();

        allArticles.forEach(article->{

            Date createDate = DateUtil.parse(article.getCreateDate());

            String year = DateUtil.format(createDate, "yyyy");

            if(yearSet.add(year)){
                article.setYear(year);
            }

            String year_month = DateUtil.format(createDate, "yyyy-MM");

            if(yearMonthSet.add(year_month)){
                article.setMonth(DateUtil.format(createDate, "MM"));
            }

            article.setDay(DateUtil.format(createDate, "dd"));
        });


        return allArticles;
    }

    /**
     * 获取文章日历数据
     * @return
     */
    @Override
    public Map<String,Object> getCalendar(){

        Map<String,Object> calendarInfo = new HashMap<String,Object>(16);

        Calendar calendar = Calendar.getInstance();
        //当前时间
        Date today = DateUtil.date(calendar);

        //一年前
        calendar.add(Calendar.YEAR,-1);
        Date lastYear = DateUtil.date(calendar);

        //一年的天数
        Integer daysNum = (int)DateUtil.betweenDay(today, lastYear, false);

        //保存日期数组
        List list = new ArrayList();

        //获取每个创建日期下的博客数
        List<Map> dateCountList = articleMapper.getBlogCountByCreateDate();

        //将日期博客数转成以日期为键，博客数为值的Map
        HashMap<String, Object> dateCountMap = new HashMap<String, Object>();

        dateCountList.forEach(e->{
            dateCountMap.put(e.get("create_date").toString(),e.get("count"));
        });

        for(int i=0;i<=daysNum;i++){
            //日期文章数数组
            List list2 = new ArrayList();
            String format = DateUtil.format(DateUtil.offsetDay(lastYear, i), DatePattern.NORM_DATE_PATTERN);
            list2.add(format);
            list2.add(dateCountMap.get(format)==null?"0":dateCountMap.get(format).toString());
            list.add(list2);
        }

        calendarInfo.put("today",DateUtil.format(today, DatePattern.NORM_DATE_PATTERN));

        calendarInfo.put("lastYear",DateUtil.format(lastYear, DatePattern.NORM_DATE_PATTERN));

        calendarInfo.put("calendarInfo",list);

        return calendarInfo;
    }

    /**
     * 插入博客
     * @param articleVO
     */
    private void insert(ArticleVO articleVO){

        Article article = (Article)articleVO;

        article.setId(DataBaseUtil.nextValue());

        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));

        article.setCreateDate(DateUtil.today());

        article.setUserId(ShiroKit.getUser().getId());

        article.setLastArticleId(articleMapper.getLastArticleId());

        article.setArticleUrl("/article/"+article.getId());

        article.setArticleImage(ArticleUtil.randomImage());

        articleMapper.insert(article);

        List<Tags> tagsList = articleVO.getTagsList();

        List<Relation> relationList = new ArrayList<Relation>();

        List<Tags> newTagsList = new ArrayList<Tags>();

        tagsList.forEach(tags->{
            if(tags.getId() == 0){
                tags.setId(DataBaseUtil.nextValue());
                newTagsList.add(tags);
            }
            Relation relation = new Relation(article.getId(),tags.getId());
            relationList.add(relation);
        });

        if(newTagsList.size() != 0){
            tagsMapper.batchInsertTags(newTagsList);
        }

        relationMapper.batchInsertArticleTagsRelation(relationList);

    }

    /**
     * 修改博客
     * @param params
     */
    private void update(Params params){

//        Article article = params.getObject("article",Article.class);
//
//        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));
//
//        article.setUpdateDate(DateUtil.today());
//
//        articleMapper.updateArticleById(article);
//
//        relationMapper.deleteLabelsByArticleId(article.getId());
//
//        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        //relationMapper.deleteCategoriesByArticleId(article.getId());

        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }
}
