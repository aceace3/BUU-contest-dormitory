package com.dormitory.service;


import com.dormitory.dao.account.RoleActionRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.model.account.SysRoleAction;
import com.dormitory.model.account.User;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleActionRepository roleActionRepository;

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        }

        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (!user.isRemove()) {
                String password = user.getPassword();
                if (password == null) {
                    throw new UnknownAccountException("No account found for user [" + username + "]");
                }
                // principal: 认证实体,可以是username,也可以是数据库对应表名
                // 密码从数据库取出的密码
                // RealName
                logger.info("开始SimpleAuthenticationInfo校验");
                return new SimpleAuthenticationInfo(user, password, getName());
            } else {
                logger.info("user remove=1");
                throw new LockedAccountException("locked user");
            }
        } else {
            logger.info("找不到user");
            throw new UnknownAccountException("cannot find user");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        logger.info("开始加载权限");
        //存入role
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().getName());
        authorizationInfo.setRoles(roles);
        logger.info("找到role完成");
        //找出所有的role拥有的actions
        List<SysRoleAction.ActionType> roleActions = roleActionRepository.listActionTypesByRoleId(user.getRole().getId());
        Set<String> permissions = new HashSet<>();
        if (roleActions != null && roleActions.size() > 0) {
            for (SysRoleAction.ActionType actionType : roleActions) {
                permissions.add(actionType.toString());
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        logger.info("放入action完成");
        return authorizationInfo;
    }
}
