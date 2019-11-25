package com.xiao.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Page;
import com.xiao.blog.model.Permission;
import com.xiao.blog.model.TreeModel;
import com.xiao.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 保存权限
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public int insert(Permission permission){
        if(permission.getId() == null)
            return permissionService.insert(permission);
        else
           return permissionService.update(permission);
    }

    /**
     * 删除权限
     * @param id
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Integer id){
        permissionService.delete(id);
    }

    /**
     * 批量删除权限
     * @param ids
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public void delete(@RequestParam("ids[]") List<Integer> ids){
        permissionService.deleteBatch(ids);
    }

    /**
     * 根据父权限获取权限列表
     * @param page
     * @return
     */
    @GetMapping("/getPermissionsByParent/{parentId}")
    @ResponseBody
    public Page<Permission> getPermissionsByParent(Page page,@PathVariable("parentId") Integer parentId){
        PageHelper.startPage(page.getPage(),page.getLimit());
        PageInfo info=new PageInfo(permissionService.getPermissionsByParent(parentId));//创建pageinfo，包含分页的信息
        page.setData(info.getList());
        page.setCode(0);
        page.setCount(info.getTotal());

        return page;
    }

    /**
     * 获取权限树
     * @param
     * @return
     */
    @PostMapping("/getPermissionTree")
    @ResponseBody
    public List<TreeModel> getPermissionTree(){
        return permissionService.getPermissionTree();
    }
}
