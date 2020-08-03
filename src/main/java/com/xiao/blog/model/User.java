package com.xiao.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private String name;

    private String avatar;

    private String address;

    private String phoneNum;

    private String birthday;

    private String email;

    private Integer gender;

    private String description;

    private Date lastLoginTime;

    private Long loginCount;

    private Date loginTime;

    private Date registeredTime;

    private String salt;

    private String userIp;

    private Role role;

}