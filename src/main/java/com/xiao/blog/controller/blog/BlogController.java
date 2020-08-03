package com.xiao.blog.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 博客首页
 * @author wangmx
 * @date 2020-01-19 16:13
 * @desc:
 */
@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TagsService tagsService;

    @RequestMapping("/article/{id}")
    public String article(@PathVariable("id") Integer id, PageRequest<ArticleVO> request, Model model){

        PageHelper.startPage(request.getPage(),request.getLimit());
        List<ArticleVO> articles = articleService.getArticleByUserId(id);
        PageInfo pageInfo=new PageInfo(articles,articles.size());

        model.addAttribute("userId",id);
        model.addAttribute("pageInfo",pageInfo);

        return "blog/article";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,Model model){

        Article article = new Article();
        article.setId(id);
        model.addAttribute("article",articleService.getArticleById(id));
        model.addAttribute("commons",articleService.getArticles(article).get(0));
        return "blog/detail";
    }


    /**
     * 分类
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/classify/{id}")
    public String classify(@PathVariable("id") Integer id,Model model){

        model.addAttribute("classifyList",articleService.classify(id));

        return "blog/classify";
    }

    /**
     * 归档
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/archive/{id}")
    public String archive(@PathVariable("id") Integer id,Model model){

        model.addAttribute("archiveVOList",articleService.archive(id));

        return "blog/archive";
    }

    /**
     * 标签云
     * @param id 用户id
     * @param model
     * @return
     */
    @RequestMapping("/tagsCloud/{id}")
    public String tagsCloud(@PathVariable("id") Integer id,Model model){

        model.addAttribute("tagsList",tagsService.getTagsList(id));

        return "blog/tagsCloud";
    }
}
