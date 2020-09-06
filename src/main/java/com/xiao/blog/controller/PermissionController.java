package com.xiao.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Permission;
import com.xiao.blog.pojo.TreeModel;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.pojo.response.CommonResponse;
import com.xiao.blog.pojo.response.PageResponse;
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
    public CommonResponse save(Permission permission){
        if(permission.getId() == null)
            return new CommonResponse<Integer>(permissionService.insert(permission));
        else
           return new CommonResponse<Integer>(permissionService.update(permission));
    }

    /**
     * 删除权限
     * @param id
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public CommonResponse<Integer> delete(@PathVariable("id") Integer id){

        return new CommonResponse<Integer>(permissionService.delete(id));
    }


    /**
     * 根据父权限获取权限列表
     * @param request
     * @return
     */
    @GetMapping("/getPermissionsByParent/{parentId}")
    @ResponseBody
    public PageResponse<Permission> getPermissionsByParent(PageRequest request, @PathVariable("parentId") Integer parentId){

        PageHelper.startPage(request.getPage(),request.getLimit());

        //创建pageinfo，包含分页的信息
        PageInfo info=new PageInfo(permissionService.getPermissionsByParent(parentId));

        return new PageResponse(info.getTotal(),info.getList());
    }

    /**
     * 获取权限树
     * @param
     * @return
     */
    @PostMapping("/getPermissionTree/{roleId}")
    @ResponseBody
    public List<TreeModel> getPermissionTree(@PathVariable("roleId") Integer roleId){
        return permissionService.getPermissionTree(roleId);
    }
}
