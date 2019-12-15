package com.xiao.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.blog.model.Role;
import com.xiao.blog.pojo.request.PageRequest;
import com.xiao.blog.pojo.response.PageResponse;
import com.xiao.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public PageResponse<Role> getAll(PageRequest request){
        PageHelper.startPage(request.getPage(),request.getLimit());
        PageInfo info=new PageInfo(roleService.getAll());//创建pageinfo，包含分页的信息


        return new PageResponse<Role>(info);
    }

    @RequestMapping("/setPermission")
    @ResponseBody
    public void setPermission(
            @RequestParam("permissions[]") List<Integer> permissions,
            @RequestParam("roleId") Integer roleId){

        roleService.setPermission(permissions,roleId);
    }

}
