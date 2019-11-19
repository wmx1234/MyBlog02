package com.xiao.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Page;
import com.xiao.blog.model.Permission;
import com.xiao.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-17 15:53
 * @Desc
 */

@Controller
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(Permission permission){

        return permissionService.insert(permission);

    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Integer id){
        permissionService.delete(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(Permission permission){

        permissionService.update(permission);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Page<Permission> getAll(Page page){
        PageHelper.startPage(page.getPage(),page.getLimit());
        PageInfo info=new PageInfo(permissionService.getAll());//创建pageinfo，包含分页的信息
        page.setData(info.getList());
        page.setCode(0);
        page.setCount(info.getTotal());

        return page;
    }
}
