package com.xiao.blog.config;

import javax.servlet.Filter;
import com.xiao.blog.shiro.factory.CustomRolesAuthorizationFilter;
import com.xiao.blog.shiro.factory.ShiroUtil;
import com.xiao.blog.shiro.realm.ShiroDbRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-15 20:24
 * @Desc
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroUtil shiroUtil;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");

        Map<String, String> filterChainDefinitionMap = shiroUtil.loadFilterChainDefinitions();
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("customRolesAuthorizationFilter", customRolesAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        shiroFilterFactoryBean.setUnauthorizedUrl("/404.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public CustomRolesAuthorizationFilter customRolesAuthorizationFilter() {

        return new CustomRolesAuthorizationFilter();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDbRealm());
        return securityManager;
    }

    @Bean
    public ShiroDbRealm shiroDbRealm() {
        ShiroDbRealm shiroDbRealm =  new ShiroDbRealm();
        shiroDbRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroDbRealm;
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }

}
