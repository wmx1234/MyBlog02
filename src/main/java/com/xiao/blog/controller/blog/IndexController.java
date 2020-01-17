package com.xiao.blog.controller.blog;

import com.xiao.blog.model.Categories;
import com.xiao.blog.model.User;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-14 22:07
 * @Desc
 */

@Controller()
@RequestMapping("/")
public class IndexController {

    @Value("${bloger.boy}")
    private int boy;

    @Value("${bloger.gril}")
    private int girl;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping({"/index","/"})
    public String index(){

        //获取所有博主
        List<User> blogers = userService.getBloger();


        return "blog/index4";
    }


    @RequestMapping({"/boy","/girl"})
    public String boy(HttpServletRequest request){
        if(request.getServerName().contains("/boy")){
            request.setAttribute("articles",articleService.getArticleByUserId(boy));
        }else{
            request.setAttribute("articles",articleService.getArticleByUserId(girl));
        }
        return "blog/prefecture";
    }

    /**
     * 文章分类
     * @param request
     * @return
     */
    @RequestMapping({"/boy/classify","/girl/classify"})
    public String classify(HttpServletRequest request){
        Categories categories = new Categories();
        String s = request.getRequestURL().toString();
        if(s.contains("/boy"))
            categories.setUserId(boy);
        else
            categories.setUserId(girl);

        request.setAttribute("classify",categoriesService.classify(categories));

        return "blog/classify";
    }

}
