package com.xiao.blog.controller.admin;

import com.xiao.blog.model.Tags;
import com.xiao.blog.pojo.response.BaseResponse;
import com.xiao.blog.pojo.response.CommonResponse;
import com.xiao.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-29 22:02
 * @Desc
 */

@Controller
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    TagsService labelService;

    @RequestMapping("/save")
    @ResponseBody
    public int save(Tags label){
        return labelService.save(label);
    }

    @RequestMapping("/add")
    public String add(Tags label, Model model){

        labelService.save(label);
        model.addAttribute("labels",labelService.getAll());
        return "/admin/mdeditor::mdeditor_labels";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public CommonResponse<List<Tags>> getAll(){
        CommonResponse result = new CommonResponse();
        result.setCode(0);
        result.setData(labelService.getAll());
        return result;
    }

    /**
     * 批量删除权限
     * @param ids
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public void delete(@RequestParam("ids[]") List<Integer> ids){
        labelService.deleteBatch(ids);
    }
}
