package com.xiao.blog.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Article;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.CategoriesVO;
import com.xiao.blog.vo.TagsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        List<ArticleVO> articles = articleService.getPageArticleList(new Article(1,1));

        PageInfo pageInfo = new PageInfo(articles);

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("topArticle",articleService.getTopArticle());

        model.addAttribute("content","晓&静");

        model.addAttribute("title","晓&静");

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

        model.addAttribute("content","分类，晓&静");

        model.addAttribute("title","分类 | 晓&静");

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

        categoriesVOList.forEach(categoriesVO->{
            if(categoriesVO.getId() == id){
                model.addAttribute("content","分类：" + categoriesVO.getName() + "，晓&静");
                model.addAttribute("title","分类：" + categoriesVO.getName() + " | 晓&静");
            }
        });


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

        model.addAttribute("content","归档 ， 晓&静");

        model.addAttribute("title","归档 | 晓&静");

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

        model.addAttribute("content","标签，晓&静");

        model.addAttribute("title","标签 | 晓&静");

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

        tagsVOList.forEach(tags->{
            if(tags.getId() == id){
                model.addAttribute("content","标签：" + tags.getName() + "，晓&静");
                model.addAttribute("title","标签：" + tags.getName() + " | 晓&静");
            }
        });

        return "blog/tags";
    }
    /**
     * 标签
     * @param model
     * @return
     */
    @RequestMapping("/about")
    public String about(Model model){

        model.addAttribute("articleCount",articleService.getArticleCount());

        model.addAttribute("tagsCount",tagsService.getTagsCount());

        model.addAttribute("categoriesCount",categoriesService.getArticleCount());

        model.addAttribute("content","关于我，晓&静");

        model.addAttribute("title","关于我 | 晓&静");

        return "blog/about";
    }

    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping("/contact")
    public String contact(Model model){

        model.addAttribute("content","留言板，晓&静");

        model.addAttribute("title","留言板 | 晓&静");

        return "blog/contact";
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @RequestMapping("/friends")
    public String friends(Model model){

        model.addAttribute("content","友情链接，晓&静");

        model.addAttribute("title","友情链接 | 晓&静");

        return "blog/friends";
    }

    @RequestMapping("/article/{id}")
    public String article(@PathVariable("id") Integer id, Model model){

        ArticleVO article = articleService.getArticleById(id);

        model.addAttribute("article",article);

        model.addAttribute("content",article.getArticleTitle()+"，晓&静");

        model.addAttribute("title",article.getArticleTitle()+" | 晓&静");

        return "blog/article";
    }

    @ResponseBody
    @GetMapping("/articles")
    public Iterable<Article> search(@RequestParam(required = false,defaultValue = "1")String name){
        return articleService.searchArticle(name);
    }

}
