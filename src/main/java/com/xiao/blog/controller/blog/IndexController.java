package com.xiao.blog.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.service.UserService;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author wangmx
 * @create 2019-11-14 22:07
 * @Desc
 */

@Controller()
@RequestMapping("/")
public class IndexController {


    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    TagsService tagsService;

    /**
     * 访问网站主页
     * @return
     */
    @RequestMapping({"/index","/"})
    public String index(Model model){
        List<ArticleVO> articles = articleService.getArticleByUserId(1);
        model.addAttribute("articles",articles);
        return "blog/index";
    }

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
     * @param model
     * @return
     */
    @RequestMapping("/categories")
    public String classify(Model model){

        model.addAttribute("classifyList",articleService.classify(1));

        return "blog/categories";
    }

    /**
     * 归档
     * @param model
     * @return
     */
    @RequestMapping("/archives")
    public String archive(Model model){

        return "blog/archives";
    }

    /**
     * 标签云
     * @param model
     * @return
     */
    @RequestMapping("/tags")
    public String tagsCloud(Model model){

        model.addAttribute("tagsList",tagsService.getTagsList(1));

        return "blog/tags";
    }
}
