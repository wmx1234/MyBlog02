package com.xiao.blog.controller.admin;

import com.xiao.blog.model.BaseResponse;
import com.xiao.blog.model.Categories;
import com.xiao.blog.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-29 21:55
 * @Desc
 */

@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping("/save")
    @ResponseBody
    public int save(Categories categories){
        return categoriesService.save(categories);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public BaseResponse<List<Categories>> getAll(){
        BaseResponse result = new BaseResponse();
        result.setCode(0);
        result.setData(categoriesService.getAll());
        return result;
    }
}
