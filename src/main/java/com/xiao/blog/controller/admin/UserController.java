package com.xiao.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.User;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.pojo.response.PageResponse;
import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangmx
 * @date 2019-11-19 10:40
 * @desc:
 */

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getAll")
    @ResponseBody
    public PageResponse<User> getAll(PageRequest request){

        PageHelper.startPage(request.getPage(),request.getLimit());
        //创建pageinfo，包含分页的信息
        PageInfo info=new PageInfo(userService.getAll());

        return new PageResponse<User>(info);
    }


}
