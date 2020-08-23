package com.xiao.blog.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String createDate;

    private String updateData;

}