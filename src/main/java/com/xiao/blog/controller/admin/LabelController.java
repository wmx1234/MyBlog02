package com.xiao.blog.controller.admin;

import com.xiao.blog.model.BaseResponse;
import com.xiao.blog.model.Label;
import com.xiao.blog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/getAll")
    @ResponseBody
    public BaseResponse<List<Label>> getAll(){
        BaseResponse result = new BaseResponse();
        result.setCode(0);
        result.setData(labelService.getAll());
        return result;
    }
}
