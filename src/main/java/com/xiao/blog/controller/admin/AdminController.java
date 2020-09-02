package com.xiao.blog.controller.admin;

import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Categories;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.User;
import com.xiao.blog.service.*;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangmx
 * @create 2019-11-14 22:07
 * @Desc
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    PermissionService permissionService;

    @Resource
    TagsService tagsService;

    @Resource
    CategoriesService categoriesService;

    @Resource
    RelationMapper relationMapper;

    @Resource
    ArticleService articleService;

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }



    @PostMapping("/login")
    public String login(User user){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        subject.login(token);

        LoginUser currentUser = userService.getLoginUser(user.getUserName());

        Session session = ShiroKit.getSession();

        session.setAttribute("user",currentUser);
        List<Permission> menu = permissionService.getMenu(currentUser.getRole().getId());
        session.setAttribute("permissions",menu);

        return "redirect:/admin/index";
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

    @RequestMapping("/article")
    public String article(Model model){
        model.addAttribute("categoriesList",categoriesService.getCategoriesVOList(ShiroKit.getUser().getId()));
        return "/admin/article";
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
        model.addAttribute("categoriesList",categoriesService.getCategoriesByField(null));
        return "/admin/categories";
    }


    @RequestMapping("/tags")
    public String tags(){

        return "/admin/tags";
    }

    @RequestMapping("/comment")
    public String comment(){

        return "/admin/comment";
    }

    @RequestMapping("/editor")
    public String write(Model model){

        model.addAttribute("articleVO", new ArticleVO(DataBaseUtil.nextValue()));
        //获取分类列表
        model.addAttribute("categoriesList",categoriesService.getCategoriesByField(new Categories(ShiroKit.getUser().getId())));
        //获取标签列表
        model.addAttribute("tagsList",tagsService.getTagsList());

        return "/admin/md_editor";
    }

    @RequestMapping("/editor/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        //model.addAttribute("articleVO", articleService.getArticleById(id));
        return "/admin/md_editor";
    }
}
