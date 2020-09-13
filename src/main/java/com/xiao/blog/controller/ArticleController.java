package com.xiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.pojo.response.BaseResponse;
import com.xiao.blog.pojo.response.CommonResponse;
import com.xiao.blog.pojo.response.PageResponse;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author wangmx
 * @create 2019-11-28 20:15
 * @Desc
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @Resource
    CategoriesService categoriesService;

    /**
     * 保存博客
     * @param articleVO
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(@RequestBody ArticleVO articleVO){
        return new CommonResponse(articleService.save(articleVO));
    }

    @RequestMapping("/getArticleVOList")
    @ResponseBody
    public PageResponse<ArticleVO> getArticleVOList(PageRequest request, Article article){

        PageHelper.startPage(request.getPage(),request.getLimit());

        PageInfo info=new PageInfo(articleService.getPageArticleList(article));

        return new PageResponse<ArticleVO>(info);
    }


    /**
     * 保存博客
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public BaseResponse delete(@PathVariable Integer id){
        return new CommonResponse(articleService.delete(id));
    }


    /**
     * 清除Es中的内容博客
     * @return
     */
    @RequestMapping("/clearEsArticle")
    @ResponseBody
    public BaseResponse clearEsArticle(){
        articleService.clearEsArticle();
        return new BaseResponse();
    }
}
