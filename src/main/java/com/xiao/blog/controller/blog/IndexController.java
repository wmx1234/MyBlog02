package com.xiao.blog.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
     * 访问博客主页
     * @return
     */
    @RequestMapping({"/index4"})
    public String index4(){
        return "blog/index4";
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
