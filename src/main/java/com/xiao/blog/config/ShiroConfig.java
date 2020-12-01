package com.xiao.blog.config;

import javax.servlet.Filter;

import com.xiao.blog.constants.ShiroConstants;
import com.xiao.blog.shiro.factory.CustomRolesAuthorizationFilter;
import com.xiao.blog.shiro.factory.ShiroUtil;
import com.xiao.blog.shiro.realm.ShiroDbRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
        //没有登录的用户请求需要登录的页面时自动跳转到登录页面。
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //成功登录
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");

        //登录的用户访问了没有被授权的资源自动跳转到的页面。
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");

        //设置自定义过滤器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();

        filtersMap.put("customRolesAuthorizationFilter", customRolesAuthorizationFilter());

        shiroFilterFactoryBean.setFilters(filtersMap);

        //获取过滤链接
        Map<String, String> filterChainDefinitionMap = shiroUtil.loadFilterChainDefinitions();

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
        securityManager.setRememberMeManager(rememberMeManager(rememberMeCookie()));
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
        credentialsMatcher.setHashAlgorithmName(ShiroConstants.HASH_ALGORITHM_NAME);
        credentialsMatcher.setHashIterations(ShiroConstants.HASH_ITERATIONS);
        return credentialsMatcher;
    }

    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    }

}
