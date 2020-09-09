package com.xiao.blog.controller;

import com.xiao.blog.model.Tags;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.pojo.response.CommonResponse;
import com.xiao.blog.service.TagsService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.TagsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-29 22:02
 * @Desc
 */

@Controller
@RequestMapping("/admin/tags")
public class TagsController {

    @Resource
    TagsService tagsService;

    @RequestMapping("/save")
    @ResponseBody
    public CommonResponse save(Tags tags){

        return new CommonResponse(tagsService.save(tags));
    }

    @RequestMapping("/add")
    public String add(Params tags, Model model){
        //labelService.save(tags);
        model.addAttribute("tagsList",tagsService.getTagsList());
        return "/admin/md_editor::md_editor_tags";
    }


    @RequestMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse delete(@PathVariable("id") Integer id){
        return new CommonResponse(tagsService.delete(id));
    }

    @RequestMapping("/getTagsList")
    @ResponseBody
    public CommonResponse<List<Tags>> getTagsList(Integer userId){
        List<Tags> tagsList = tagsService.getTagsList();
        return new CommonResponse(tagsList);
    }

    @RequestMapping("/getTagsVOList")
    @ResponseBody
    public CommonResponse<List<TagsVO>> getTagsVOList(){

        List<TagsVO> tagsVOList = tagsService.getTagsVOList();
        return new CommonResponse(tagsVOList);
    }


}
