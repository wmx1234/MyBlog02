package com.xiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiao.blog.model.Categories;
import com.xiao.blog.model.Permission;
import com.xiao.blog.service.*;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.util.KaptchaUtil;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
    PermissionService permissionService;

    @Resource
    TagsService tagsService;

    @Resource
    CategoriesService categoriesService;

    @Resource
    ArticleService articleService;

    @Resource
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("kaptchaOnOff",KaptchaUtil.getKaptchaOnOff());
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(LoginUser user,HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        //校验验证码
        KaptchaUtil.verifyVerCode(user);

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());

        token.setRememberMe(user.getRememberMe()==null?false:user.getRememberMe());

        subject.login(token);

        //根据用户名获取登录用户
        LoginUser currentUser = userService.getLoginUser(user.getUserName());

        Session session = ShiroKit.getSession();

        session.setAttribute("user",currentUser);

        List<Permission> menu = permissionService.getMenu(currentUser.getRole().getId());

        session.setAttribute("permissions",menu);

        return "redirect:index";
    }


    /**
     * 退出登录
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(Model model){
        Subject subject = SecurityUtils.getSubject();
        ShiroKit.getSession().removeAttribute("user");
        model.addAttribute("kaptchaOnOff",KaptchaUtil.getKaptchaOnOff());
        subject.logout();
        return "admin/login";
    }

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 博客管理页面
     * @param model
     * @return
     */
    @RequestMapping("/article")
    public String article(Model model,ArticleVO article){
        model.addAttribute("categoriesList",categoriesService.getCategoriesVOList());
        return "admin/article";
    }

    /**
     * 权限管理页面
     * @param model
     * @return
     */
    @RequestMapping("/permission")
    public String permission(Model model){

        model.addAttribute("permissions",permissionService.getPermissionsByParent(0));
        return "admin/permission";
    }

    /**
     * 角色管理
     * @param model
     * @return
     */
    @RequestMapping("/role")
    public String role(Model model){
        return "/admin/role";
    }

    /**
     * 用户管理
     * @param model
     * @return
     */
    @RequestMapping("/user")
    public String user(Model model){
        return "/admin/user";
    }

    /**
     * 分类管理
     * @param model
     * @return
     */
    @RequestMapping("/categories")
    public String categories(Model model){
        model.addAttribute("categoriesList",categoriesService.getCategoriesByField(null));
        return "admin/categories";
    }

    /**
     * 标签管理
     * @return
     */
    @RequestMapping("/tags")
    public String tags(){

        return "admin/tags";
    }

    /**
     * 评论管理
     * @return
     */
    @RequestMapping("/comment")
    public String comment(){

        return "admin/comment";
    }

    /**
     * 新增博客
     * @param model
     * @return
     */
    @RequestMapping("/editor")
    public String write(Model model){
        Integer id = DataBaseUtil.nextValue();

        ShiroKit.setSessionAttr("articleId",id);

        model.addAttribute("article", new ArticleVO(id));
        //获取分类列表
        model.addAttribute("categoriesList",categoriesService.getCategoriesByField(new Categories()));
        PageHelper.startPage(0,15);

        PageInfo info=new PageInfo(tagsService.getTagsList());
        //获取标签列表
        model.addAttribute("tagsList",info.getList());

        return "admin/md_editor";
    }

    /**
     * 个人页面
     * @param model
     * @return
     */
    @RequestMapping("/personal")
    public String personal(Model model){
        return "admin/personal";
    }

    /**
     * 编辑博客
     * @param model
     * @return
     */
    @RequestMapping("/editor/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){

        ShiroKit.setSessionAttr("articleId",id);

        model.addAttribute("article", articleService.getArticleById(id));

        //获取分类列表
        model.addAttribute("categoriesList",categoriesService.getCategoriesByField(new Categories()));
        //获取标签列表
        model.addAttribute("tagsList",tagsService.getTagsList());

        return "admin/md_editor";
    }

    @RequestMapping("/getVerCode")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captcha = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // 将生成的验证码保存在session中
            String createText = defaultKaptcha.createText();
            //获取数学运算式文本
            String mathStr = createText.substring(0, createText.lastIndexOf("@"));
            //获取运算式结果
            String result = createText.substring(createText.lastIndexOf("@") + 1);

            request.getSession().setAttribute("rightCode", result);

            BufferedImage bi = defaultKaptcha.createImage(mathStr);

            ImageIO.write(bi, "jpg", out);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captcha = out.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sout = response.getOutputStream();
        sout.write(captcha);
        sout.flush();
        sout.close();
    }
}
