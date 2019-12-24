package com.xiao.blog.controller.blog;

import com.xiao.blog.model.User;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Value("${bloger.boy}")
    private int boy;

    @Value("${bloger.gril}")
    private int gril;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/test")
    public String test(){
        return "/test";
    }


    @RequestMapping({"/index","/"})
    public String index(){

        //获取所有博主
        List<User> blogers = userService.getBloger();


        return "blog/index4";
    }


    @RequestMapping("/boy")
    public String boy(Model model){
        model.addAttribute("articles",articleService.getArticleByUserId(boy));
        return "blog/prefecture";
    }



}
