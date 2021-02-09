package com.dormitory.config;

import com.dormitory.service.ShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    private EhCacheManager cacheManager = null;

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ShiroFilterFactoryBean shiroFilterFactoryBeanFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/LoginController/**", "anon");
        filterChainDefinitionMap.put("/MapController/**", "anon");
        filterChainDefinitionMap.put("/RegisterController/**", "anon");
        filterChainDefinitionMap.put("/account/login", "anon");
        filterChainDefinitionMap.put("/account/logout", "anon");
        filterChainDefinitionMap.put("/account/test/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/configuration/security/**", "anon");
        filterChainDefinitionMap.put("/configuration/ui/**", "anon");
//        filterChainDefinitionMap.put("/MyClassPageController/**","anon");
//        filterChainDefinitionMap.put("/StuListOfClassController/**", "anon");
//        filterChainDefinitionMap.put("/TeacherMePageController/**", "anon");
//        filterChainDefinitionMap.put("/AskForLeaveController/**", "anon");






        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/account/logout", "logout");

        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/account/invalid");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边-->

        //filterChainDefinitionMap.put("/account/user/**", "perms[ACCOUNT_USER_DEPARTMENT]");

        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        if(cacheManager == null) {
            cacheManager = getEhCacheManager();
        }
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        return sessionDAO;
    }

    /**
     * 身份认证realm; (账号密码校验；权限)
     *
     * @return
     */
    @Bean
    public ShiroRealm myShiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        cacheManager = em;
        return em;
    }

}
