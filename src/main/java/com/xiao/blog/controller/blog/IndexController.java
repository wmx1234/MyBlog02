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

    @RequestMapping({"/index","/"})
    public String index(){
        return "blog/index";
    }


    @RequestMapping("/boy")
    public String boy(){
        return "blog/prefecture";
    }
}
