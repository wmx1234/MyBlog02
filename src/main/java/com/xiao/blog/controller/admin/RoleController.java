package com.xiao.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Page;
import com.xiao.blog.model.Role;
import com.xiao.blog.service.RoleService;
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
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Page<Role> getAll(Page page){
        PageHelper.startPage(page.getPage(),page.getLimit());
        PageInfo info=new PageInfo(roleService.getAll());//创建pageinfo，包含分页的信息
        page.setData(info.getList());
        page.setCode(0);
        page.setCount(info.getTotal());

        return page;
    }
}
