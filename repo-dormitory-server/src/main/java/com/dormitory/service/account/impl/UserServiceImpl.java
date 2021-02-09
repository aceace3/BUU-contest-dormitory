package com.dormitory.service.account.impl;

import com.dormitory.dao.account.RoleActionRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.exception.UserException;
import com.dormitory.model.account.QUser;
import com.dormitory.model.account.SysRoleAction;
import com.dormitory.model.account.User;
import com.dormitory.service.BaseServiceImpl;
import com.dormitory.service.account.UserService;
import com.dormitory.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleActionRepository roleActionRepository;

    @Override
    public Iterable<User> listUsers() {
        QUser q = QUser.user;
        return userRepository.findAll(q.remove.eq(false));
    }

    @Override
    public Iterable<User> listByRoleId(int roleId) {
        QUser q = QUser.user;
        return userRepository.findAll(q.role.id.eq(roleId));
    }

    @Override
    public Iterable<User> listByPermissionId(int permissionId) {
        QUser q = QUser.user;
        return userRepository.findAll(q.permission.id.eq(permissionId));
    }

    @Override
    public User updateUserPassword(int id, String password, SessionVO svo) throws Exception {
        List<SysRoleAction.ActionType> actionTypes = roleActionRepository.listActionTypesByRoleId(svo.getSvoRoleId());
        //如果没有数据管理权限,只能修改自己的密码
        if(!actionTypes.contains(SysRoleAction.ActionType.API_ALLOW_ADMIN)) {
            if(svo.getSvoId() != id) {
                throw UserException.CHANGE_OTHER_PASSWORD;
            }
        }
        User user = this.findById(User.class, id);
        if (user == null) {
            throw UserException.NOT_FOUND;
        }
        if (!StringUtil.isEmpty(password)) {
            user.setPassword(password);
        } else {
            throw UserException.EMPTY_PASSWORD;
        }
        this.update(user);
        return user;
    }
}
