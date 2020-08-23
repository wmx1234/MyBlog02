package com.xiao.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.pojo.response.BaseResponse;
import com.xiao.blog.pojo.response.PageResponse;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.ArticleVO;
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

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(@RequestBody Article article){

        articleService.save(article);

        return new BaseResponse();
    }

    @RequestMapping("/getArticleVOList")
    @ResponseBody
    public PageResponse<ArticleVO> getArticleVOList(PageRequest request){
        PageHelper.startPage(request.getPage(),request.getLimit());
        //创建pageinfo，包含分页的信息
        PageInfo info=new PageInfo(articleService.getAllArticles());

        return new PageResponse<ArticleVO>(info);
    }

}
