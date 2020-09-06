package com.xiao.blog.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.CategoriesVO;
import com.xiao.blog.vo.TagsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客首页
 * @author wangmx
 * @date 2020-01-19 16:13
 * @desc:
 */
@Controller()
@RequestMapping("/")
public class BlogController {

    @Resource
    ArticleService articleService;

    @Resource
    TagsService tagsService;

    @Resource
    CategoriesService categoriesService;

    /**
     * 访问网站主页
     * @param pageNum 当前页
     * @param model
     * @return
     */
    @RequestMapping({"/index","/"})
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageNum,Model model){

        PageHelper.startPage(pageNum, 9);

        List<ArticleVO> articles = articleService.getAllArticles();

        PageInfo pageInfo = new PageInfo(articles);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("topArticle",articleService.getTopArticle());

        return "blog/index";
    }

    /**
     * 分类
     * @param model
     * @return
     */
    @RequestMapping("/categories")
    public String classify(Model model){

        List<CategoriesVO> categoriesVOList = categoriesService.getCategoriesVOList();

        model.addAttribute("categoriesList", categoriesVOList);

        model.addAttribute("flag", false);

        return "blog/categories";
    }

    /**
     * 分类
     * @param model
     * @return
     */
    @RequestMapping("/categories/{id}")
    public String classify(@PathVariable("id") Integer id,Model model){

        List<CategoriesVO> categoriesVOList = categoriesService.getCategoriesVOList();

        model.addAttribute("categoriesList", categoriesVOList);

        ArticleVO article = new ArticleVO();

        article.setCategoriesId(id);

        List<ArticleVO> articleList = articleService.getArticleListByField(article);

        model.addAttribute("articleList",articleList);

        return "blog/categories";
    }

    /**
     * 归档
     * @param model
     * @return
     */
    @RequestMapping("/archives")
    public String archive(@RequestParam(required = false,defaultValue = "1")Integer pageNum,Model model){

        PageHelper.startPage(pageNum, 9);

        List<ArticleVO> archive = articleService.archive();

        PageInfo pageInfo = new PageInfo(archive);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("blogCalendar", JSONUtil.parseObj(articleService.getCalendar()));
        return "blog/archives";
    }

    /**
     * 标签
     * @param model
     * @return
     */
    @RequestMapping("/tags")
    public String tagsCloud(Model model){

        List<TagsVO> tagsVOList = tagsService.getTagsVOList();

        model.addAttribute("tagsList", tagsVOList);

        return "blog/tags";
    }

    /**
     * 根据标签获取博客
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tags/{id}")
    public String getArticleListByTagsId(@PathVariable("id") Integer id, Model model){

        List<TagsVO> tagsVOList = tagsService.getTagsVOList();

        List<ArticleVO> articleList = articleService.getArticleListByTagsId(id);

        model.addAttribute("tagsList", tagsVOList);

        model.addAttribute("articleList",articleList);

        return "blog/tags";
    }
    /**
     * 标签
     * @param model
     * @return
     */
    @RequestMapping("/about")
    public String about(Model model){

        return "blog/about";
    }

    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping("/contact")
    public String contact(Model model){

        return "blog/contact";
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @RequestMapping("/friends")
    public String friends(Model model){

        return "blog/friends";
    }

    @RequestMapping("/article/{id}")
    public String article(@PathVariable("id") Integer id, Model model){

        ArticleVO article = articleService.getArticleById(id);

        model.addAttribute("article",article);

        return "blog/article";
    }


}
