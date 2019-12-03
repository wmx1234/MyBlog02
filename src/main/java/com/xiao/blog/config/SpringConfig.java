package com.xiao.blog.config;

import com.xiao.blog.util.DataBaseUtil;
import com.xiao.blog.util.ToolUtil;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-12-03 20:52
 * @Desc
 */

@Configuration
public class SpringConfig {

    @Bean(initMethod="init")
    public DataBaseUtil databaseUtil() {
        return new DataBaseUtil();
    }

}
