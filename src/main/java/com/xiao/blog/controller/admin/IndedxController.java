package com.xiao.blog.controller.admin;

import com.xiao.blog.model.Role;
import com.xiao.blog.model.User;
import com.xiao.blog.service.PermissionService;
import com.xiao.blog.service.UserService;
import com.xiao.blog.shiro.ShiroKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangmx
 * @create 2019-11-14 22:07
 * @Desc
 */
@Controller
@RequestMapping("/admin")
public class IndedxController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }


    @PostMapping("/login")
    public String login(User user, Model model){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        subject.login(token);

        User currentUser = userService.getUser(user.getUserName());

        Session session = ShiroKit.getSession();

        session.setAttribute("user",currentUser);

        model.addAttribute("user",currentUser);
        model.addAttribute("menus",permissionService.getPermissionsByRole(1));

        return "/admin/index";
    }

    @RequestMapping("/permission")
    public String permission(Model model){

        Session session = ShiroKit.getSession();

        User currentUser = (User)session.getAttribute("user");

        model.addAttribute("user",currentUser);
        model.addAttribute("menus",permissionService.getPermissionsByRole(1));

        model.addAttribute("permissions",permissionService.getPermissionsByRole(1));

        return "/admin/permission";
    }


}
