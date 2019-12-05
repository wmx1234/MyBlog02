package com.xiao.blog.controller.admin;

import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.Role;
import com.xiao.blog.model.User;
import com.xiao.blog.service.*;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.util.ToolUtil;
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

    @Autowired
    LabelService labelService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    RelationMapper relationMapper;

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }


    @PostMapping("/login")
    public String login(User user, Model model){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        subject.login(token);

        User currentUser = userService.getLoginUser(user.getUserName());

        Session session = ShiroKit.getSession();

        session.setAttribute("user",currentUser);
        session.setAttribute("permissions",permissionService.getPermissionsByRole(currentUser.getRole().getId()));

        return "/admin/index";
    }



    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/admin/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("/permission")
    public String permission(Model model){

        model.addAttribute("permissions",permissionService.getPermissionsByParent(0));
        return "/admin/permission";
    }

    @RequestMapping("/role")
    public String role(Model model){
        return "/admin/role";
    }

    @RequestMapping("/user")
    public String user(Model model){
        return "/admin/user";
    }

    @RequestMapping("/categories")
    public String categories(Model model){
        model.addAttribute("categoriesList",categoriesService.getAll());
        return "/admin/categories";
    }


    @RequestMapping("/label")
    public String label(Model model){

        return "/admin/label";
    }

    @RequestMapping("/editor")
    public String write(Model model){

        model.addAttribute("articleId", DataBaseUtil.nextValue());
        model.addAttribute("categoriesList",categoriesService.getAll());
        model.addAttribute("labels",labelService.getAll());
        return "/admin/mdeditor";
    }
}
