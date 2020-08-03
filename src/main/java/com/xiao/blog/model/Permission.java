package com.xiao.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Permission implements Serializable {

    private Integer id;

    private String name;

    private String url;

    private String icon;

    private String description;

    private Integer parentId;

    private String open;

    private List<Role> roles;

}