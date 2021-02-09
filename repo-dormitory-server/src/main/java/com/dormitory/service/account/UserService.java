package com.dormitory.service.account;

import com.dormitory.dto.SessionVO;
import com.dormitory.model.account.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Iterable<User> listUsers();

    Iterable<User> listByRoleId(int roleId);

    Iterable<User> listByPermissionId(int permissionId);

    User updateUserPassword(int id, String password, SessionVO svo) throws Exception;

}
