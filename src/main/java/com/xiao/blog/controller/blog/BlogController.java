package com.xiao.blog.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.service.UserService;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    UserService userService;
    @Resource
    ArticleService articleService;
    @Resource
    TagsService tagsService;

    /**
     * 访问网站主页
     * @return
     */
    @RequestMapping({"/index","/"})
    public String index(PageRequest<ArticleVO> request, Model model){

        PageHelper.startPage(1,10);
        //PageHelper.startPage(request.getPage(),request.getLimit());
        List<ArticleVO> articles = articleService.getArticles(null);

        PageInfo pageInfo = new PageInfo(articles);

        model.addAttribute("pageInfo",pageInfo);

        return "blog/index";
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
     * 标签
     * @param model
     * @return
     */
    @RequestMapping("/tags")
    public String tagsCloud(Model model){

        model.addAttribute("tagsList",tagsService.getTagsList(1));

        return "blog/tags";
    }

    /**
     * 标签
     * @param model
     * @return
     */
    @RequestMapping("/about")
    public String about(Model model){

        model.addAttribute("tagsList",tagsService.getTagsList(1));

        return "blog/about";
    }

    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping("/contact")
    public String contact(Model model){

        model.addAttribute("tagsList",tagsService.getTagsList(1));

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
