package com.xiao.blog.util;

import com.xiao.blog.mapper.RelationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author wangmx
 * @create 2019-12-03 20:56
 * @Desc
 */
public class DataBaseUtil {

    @Autowired
    private RelationMapper relationMapper;


    private static DataBaseUtil dataBaseUtil;

    @PostConstruct
    public void init() {

        dataBaseUtil = this;
        dataBaseUtil.relationMapper = this.relationMapper;

    }


    public static int nextValue(){

        return dataBaseUtil.relationMapper.nextValue();
    }

}
