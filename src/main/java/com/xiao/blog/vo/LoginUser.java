package com.xiao.blog.vo;

import com.xiao.blog.model.Role;
import com.xiao.blog.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser extends User {

    private RoleVO role;

    private String vercode;

}
