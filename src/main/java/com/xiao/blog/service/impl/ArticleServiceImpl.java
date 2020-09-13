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
import com.xiao.blog.repository.ArticleRepository;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.ArticleUtil;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.vo.ArticleVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    ArticleRepository articleRepository;

    @Override
    public Article save(ArticleVO articleVO) {

        if(articleVO.getId() == null){
            return insert(articleVO);
        }else{
            return update(articleVO);
        }
    }

    @Override
    public int delete(Integer id) {
        //删除和标签关联关系
        relationMapper.deleteRelationByArticleId(id);
        //删除es中的博客
        articleRepository.deleteArticleById(id);
        //删除博客
        return articleMapper.delete(id);
    }



    @Override
    public List<ArticleVO> getArticleListByTagsId(Integer id) {

        ArticleVO article = new ArticleVO();

        article.setTagsId(id);

        List<ArticleVO> articleListByField = articleMapper.getArticleListByField(article);

        return articleListByField;

    }

    @Override
    public List<ArticleVO> getPageArticleList(Article article) {
        return articleMapper.getPageArticleList(article);
    }

    @Override
    public List<ArticleVO> getArticleListByField(ArticleVO article) {
        return articleMapper.getArticleListByField(article);
    }

    @Override
    public ArticleVO getArticleById(int id) {

        ArticleVO article = articleMapper.getArticleById(id);

        ArticleVO articleVO = new ArticleVO();

        if(article.getLastArticleId() != null){
            articleVO.setId(article.getLastArticleId());
            article.setLastArticle(articleMapper.getArticleListByField(articleVO).get(0));
        }

        if(article.getNextArticleId() != null){
            articleVO.setId(article.getNextArticleId());
            article.setNextArticle(articleMapper.getArticleListByField(articleVO).get(0));
        }
        return article;
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

        List<ArticleVO> allArticles = articleMapper.getPageArticleList(new Article());

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

    @Override
    public Iterable<Article> searchArticle(String name) {

        Iterable<Article> search = articleRepository.search(QueryBuilders
                .multiMatchQuery(name,"articleTitle","articleContent"));
        return search;
    }

    @Override
    public void clearEsArticle() {
        articleRepository.deleteAll();
    }


    /**
     * 插入博客
     * @param articleVO
     */
    @Transactional
    public Article insert(ArticleVO articleVO){

        Article article = (Article)articleVO;

        articleVO.setId(DataBaseUtil.nextValue());

        article.setArticleDigest(ArticleUtil.extractSummary(article.getArticleHtmlContent()));

        article.setCreateDate(DateUtil.today());

        article.setUserId(ShiroKit.getUser().getId());

        Integer lastArticleId = articleMapper.getLastArticleId();

        if(lastArticleId != null){

            article.setLastArticleId(lastArticleId);

            updateLastArticle(lastArticleId,article.getId());
        }

        article.setArticleUrl("/article/"+article.getId());

        article.setArticleImage(ArticleUtil.randomImage());

        if(article.getTop() == 1){
            articleMapper.quitTop();
        }

        articleMapper.insert(article);

        insertTagsAndUpdateRelation(articleVO.getTagsList(),article);

        articleRepository.save(article);



        return article;
    }

    public int getArticleCount(){
        return articleMapper.getArticleCount();
    }

    /**
     * 修改博客
     * @param articleVO
     */
    private Article update(ArticleVO articleVO){

        Article article = (Article)articleVO;

        if(article.getArticleHtmlContent() != null){
            article.setArticleDigest(ArticleUtil.extractSummary(article.getArticleHtmlContent()));
        }

        article.setUpdateDate(DateUtil.today());

        articleMapper.update(article);

        relationMapper.deleteRelationByArticleId(article.getId());

        insertTagsAndUpdateRelation(articleVO.getTagsList(),article);

        //删除es中的博客
        articleRepository.deleteArticleById(articleVO.getId());

        articleRepository.save(article);

        return article;
    }

    /**
     * 根据tagsList插入博客和标签的对照关系，并新增数据库中不存在的标签
     * @param tagsList
     * @param article
     */
    private void insertTagsAndUpdateRelation(List<Tags> tagsList,Article article){
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

    private void updateLastArticle(Integer lastArticleId,Integer currentArticleId){

        ArticleVO lastArticle = new ArticleVO();

        lastArticle.setId(lastArticleId);

        lastArticle.setNextArticleId(currentArticleId);

        update(lastArticle);
    }


}
