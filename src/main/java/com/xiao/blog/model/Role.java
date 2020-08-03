package com.xiao.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private Integer id;

    private String description;

    private String name;

    private String roleName;

}