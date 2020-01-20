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
import org.springframework.web.bind.annotation.PathVariable;
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

    
    /**
     * 访问博客主页
     * @return
     */
    @RequestMapping({"/index","/"})
    public String index(){
        return "blog/index";
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
