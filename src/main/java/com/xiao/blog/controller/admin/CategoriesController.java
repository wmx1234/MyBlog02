package com.xiao.blog.controller.admin;

import com.xiao.blog.model.Categories;
import com.xiao.blog.pojo.response.CommonResponse;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 保存分类
     * @param categories
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public CommonResponse save(Categories categories){

        return new CommonResponse(categoriesService.save(categories));
    }

    /**
     * 博客编辑页面新增分类
     * @param categories
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Categories categories, Model model){
        categoriesService.save(categories);
        model.addAttribute("categoriesList",categoriesService.getCategoriesVOList(ShiroKit.getUser().getId()));
        return "/admin/md_editor::md_editor_categories";
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse delete(@PathVariable Integer id){

        return new CommonResponse(categoriesService.delete(id));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public CommonResponse<List<Categories>> getAll(){

        return new CommonResponse(categoriesService.getCategoriesByField(null));

    }

    @RequestMapping("/getCategoriesVOList")
    @ResponseBody
    public CommonResponse<List<CategoriesVO>> getCategoriesVOList(){
        return new CommonResponse(categoriesService.getCategoriesVOList(ShiroKit.getUser().getId()));
    }
}
