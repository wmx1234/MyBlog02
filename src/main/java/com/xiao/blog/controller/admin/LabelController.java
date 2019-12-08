package com.xiao.blog.controller.admin;

import com.xiao.blog.model.BaseResponse;
import com.xiao.blog.model.Label;
import com.xiao.blog.service.LabelService;
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
    LabelService labelService;

    @RequestMapping("/save")
    @ResponseBody
    public int save(Label label){
        return labelService.save(label);
    }

    @RequestMapping("/add")
    public String add(Label label, Model model){

        labelService.save(label);
        model.addAttribute("labels",labelService.getAll());
        return "/admin/mdeditor::mdeditor_labels";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public BaseResponse<List<Label>> getAll(){
        BaseResponse result = new BaseResponse();
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
