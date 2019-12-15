package com.xiao.blog.controller.admin;

import com.xiao.blog.model.Params;
import com.xiao.blog.pojo.response.BaseResponse;
import com.xiao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangmx
 * @create 2019-11-28 20:15
 * @Desc
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(@RequestBody Params params){

        articleService.save(params);

        return new BaseResponse();
    }

}
