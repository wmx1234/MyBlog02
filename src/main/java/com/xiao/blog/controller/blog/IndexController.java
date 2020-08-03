package com.xiao.blog.controller.blog;

import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author wangmx
 * @create 2019-11-14 22:07
 * @Desc
 */

@Controller()
@RequestMapping("/")
public class IndexController {

    @Value("${blog.boy}")
    private int boy;


    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;



    /**
     * 访问网站主页
     * @return
     */
    @RequestMapping({"/index","/"})
    public String index(){
        return "blog/index/index";
    }



    @RequestMapping({"/blog"})
    public String blog(Model model){
        model.addAttribute("articles",articleService.getArticleByUserId(boy));

        return "blog/blog";
    }

    @RequestMapping({"/blog2"})
    public String blog2(Model model){


        return "blog/index6";
    }


    @RequestMapping("/archive")
    public String archive(Model model){
        model.addAttribute("archiveVOList",articleService.archive(boy));

        return "blog/index5::index_archive";
    }

    /**
     * 爱情足迹页面
     * @return
     */
    @RequestMapping("/loveTrack")
    public String loveTrack(){
        return "blog/love_track";
    }


}
