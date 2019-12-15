package com.xiao.blog.controller.admin;

import com.xiao.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangmx
 * @create 2019-12-14 14:49
 * @Desc
 */
@Controller
@RequestMapping
public class PersonController {

    @Autowired
    UserService userService;

    public void boy(Model model){



    }

}
