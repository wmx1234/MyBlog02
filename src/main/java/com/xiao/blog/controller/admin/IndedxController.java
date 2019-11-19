package com.xiao.blog.controller.admin;

import com.xiao.blog.model.Permission;
import com.xiao.blog.model.Role;
import com.xiao.blog.model.User;
import com.xiao.blog.service.PermissionService;
import com.xiao.blog.service.RoleService;
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

import java.util.List;

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
    RoleService roleService;

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
        session.setAttribute("permissions",permissionService.getPermissionsByRole(roleService.getRoleByUser(currentUser.getId())));

        return "/admin/index";
    }

    @RequestMapping("/permission")
    public String permission(Model model){

        Session session = ShiroKit.getSession();

        User currentUser = (User)session.getAttribute("user");

        model.addAttribute("user",currentUser);
        model.addAttribute("menus",permissionService.getPermissionsByRole(1));

        //model.addAttribute("permissions",permissionService.getAll());

        return "/admin/permission";
    }

    @RequestMapping("/role")
    public String role(Model model){

        Session session = ShiroKit.getSession();

        User currentUser = (User)session.getAttribute("user");

        model.addAttribute("user",currentUser);
        //model.addAttribute("menus",permissionService.getPermissionsByRole(1));

        //model.addAttribute("permissions",permissionService.getAll());

        return "/admin/role";
    }
}
