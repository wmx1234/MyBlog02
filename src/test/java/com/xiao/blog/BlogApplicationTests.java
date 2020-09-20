package com.xiao.blog;

import cn.hutool.core.util.XmlUtil;
import com.xiao.blog.model.Article;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    void contextLoads() {


    }


    @Test
    public  void test02(){
        System.out.println(elasticsearchTemplate);



        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        highlightBuilder.field("articleContent");

        highlightBuilder.field("articleTitle");
        highlightBuilder.preTags("<em>").postTags("</em>");


        // 组合查询，boost即为权重，数值越大，权重越大
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.multiMatchQuery("keyword", "articleContent").boost(3))
                .should(QueryBuilders.multiMatchQuery("keyword", "articleTitle").boost(2));

        searchQuery.withQuery(queryBuilder);


        searchQuery.withHighlightBuilder(highlightBuilder);

        List<Article>  list = elasticsearchTemplate.queryForList(searchQuery.build(),Article.class);

        System.out.println(list);
    }

    @Test
    public AggregatedPage<Article> queryByPageHigh(String keyword) {

//        //查询设置
//        BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
//        queryBuilder.should(QueryBuilders.matchQuery("articleTitle",keyword))
//                .should(QueryBuilders.matchQuery("articleContent",keyword));
//
//        //高亮设置
//        HighlightBuilder highlightBuilder=new HighlightBuilder();
//        highlightBuilder.preTags("<em>")
//                .postTags("</em>")
//                .field("articleTitle")
//                .field("articleContent");
//        //searchQueryBuilder 可以将多个条件组合在一起
//        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
//        //高亮显示
//        searchQueryBuilder.withHighlightBuilder(highlightBuilder);
//
//        //查询条件
//        searchQueryBuilder.withQuery(queryBuilder);
//        //排序条件
//        //searchQueryBuilder.withSort()

        Pageable pageable = PageRequest.of(0, 10);

        String preTag = "<font color='#dd4b39'>";//google的色值
        String postTag = "</font>";

        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchQuery("articleTitle", keyword)).
                withQuery(QueryBuilders.matchQuery("articleContent", keyword)).
                withHighlightFields(new HighlightBuilder.Field("articleTitle").preTags(preTag).postTags(postTag),
                        new HighlightBuilder.Field("articleContent").preTags(preTag).postTags(postTag)).build();
        searchQuery.setPageable(pageable);

        //List<Article> articles = elasticsearchTemplate.queryForList(searchQueryBuilder.build(),Article.class);
        AggregatedPage<Article> ideas = elasticsearchTemplate.queryForPage(searchQuery, Article.class, new SearchResultMapper() {



            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Article> chunk = new ArrayList<Article>();
                for (SearchHit searchHit : response.getHits()) {
                    System.out.println(searchHit);
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    Article idea = new Article();
                    //name or memoe
                    HighlightField ideaTitle = searchHit.getHighlightFields().get("articleTitle");
                    if (ideaTitle != null) {
                        idea.setArticleTitle(ideaTitle.fragments()[0].toString());
                    }
                    HighlightField ideaContent = searchHit.getHighlightFields().get("articleContent");
                    if (ideaContent != null) {
                        idea.setArticleContent(ideaContent.fragments()[0].toString());
                    }

                    chunk.add(idea);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) chunk);
                }
                return null;
            }
        });
        System.out.println(ideas);
        return null;
    }

}
