package com.xiao.blog.controller.blog;

import com.xiao.blog.model.Categories;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangmx
 * @date 2020-01-19 16:16
 * @desc:
 */

@RequestMapping("/girl")
@Controller
public class GirlController {

    @Value("${blog.girl}")
    private int girl;


    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping({"/",""})
    public String boy(Model model){

        model.addAttribute("title","女主专区");
        model.addAttribute("articles",articleService.getArticleByUserId(girl));
        model.addAttribute("user",userService.getUserById(girl));
        model.addAttribute("url","girl");

        return "blog/prefecture";
    }

    /**
     * 分类
     * @param model
     * @return
     */
    @RequestMapping("/classify")
    public String classify(Model model){

        model.addAttribute("classify",articleService.classify(girl));
        model.addAttribute("url","girl");
        return "blog/classify";
    }

    /**
     * 归档
     * @param model
     * @return
     */
    @RequestMapping("/archive")
    public String archive(Model model){
        model.addAttribute("url","girl");
        return "blog/archive";
    }

    /**
     * 标签云
     * @param model
     * @return
     */
    @RequestMapping("/tagsCloud")
    public String tagsCloud(Model model){
        model.addAttribute("url","girl");
        return "blog/tagsCloud";
    }

}
