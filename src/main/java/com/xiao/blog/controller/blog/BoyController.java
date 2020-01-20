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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangmx
 * @date 2020-01-19 16:13
 * @desc:
 */
@RequestMapping("/boy")
@Controller
public class BoyController {

    @Value("${blog.boy}")
    private int boy;


    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping({"/",""})
    public String boy(Model model){

        model.addAttribute("title","男主专区");
        model.addAttribute("articles",articleService.getArticleByUserId(boy));
        model.addAttribute("user",userService.getUserById(boy));

        return "blog/prefecture";
    }

    /**
     * 分类
     * @param model
     * @return
     */
    @RequestMapping("/classify")
    public String classify(Model model){
        Categories categories = new Categories();

        categories.setUserId(boy);

        model.addAttribute("classify",categoriesService.classify(categories));

        return "blog/classify";
    }

    /**
     * 归档
     * @param model
     * @return
     */
    @RequestMapping("/archive")
    public String archive(Model model){
        model.addAttribute("archives",articleService.archive(boy));
        return "blog/archive";
    }

    /**
     * 标签云
     * @param model
     * @return
     */
    @RequestMapping("/tagsCloud")
    public String tagsCloud(Model model){

        return "blog/tagsCloud";
    }
}
