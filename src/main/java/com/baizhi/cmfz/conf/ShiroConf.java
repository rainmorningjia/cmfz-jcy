package com.baizhi.cmfz.conf;

import com.baizhi.cmfz.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Miles
 * @Title: ShiroConf
 * @ProjectName cmfz-jcy
 * @Date 2019/1/4--15:09
 */
@Configuration
public class ShiroConf {

    //用户自定义Realm注入
    @Bean
    public MyRealm getRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(getCredentialMatcher());
        return myRealm;
    }

    // 核心对象 配置管理层
    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filtermap = new HashMap<>();
        filtermap.put("/image/**","anon");
        filtermap.put("/img/**","anon");
        filtermap.put("/themes/**","anon");
        filtermap.put("/css/**","anon");
        filtermap.put("/css/**","anon");
        filtermap.put("/img/createImg","anon");
        filtermap.put("/script/**","anon");
        filtermap.put("/script/**","anon");
        filtermap.put("/manager/loginManagerName","anon");
        filtermap.put("/manager/loginManager","anon");
        filtermap.put("//www.w3.org/1999/xhtml","anon");
        filtermap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtermap);
        return shiroFilterFactoryBean;

    }
    //选择凭证匹配器
    @Bean
    public CredentialsMatcher getCredentialMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
    //缓存
    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager=new EhCacheManager();
        return ehCacheManager;
    }
}
