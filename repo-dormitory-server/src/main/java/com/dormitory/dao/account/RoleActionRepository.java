package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.account.RoleAction;
import com.dormitory.model.account.SysRoleAction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleActionRepository extends BaseRepository<RoleAction> {

    @Modifying
    @Query("delete from RoleAction where role.id = :roleId")
    void deleteOldRelation(@Param("roleId") int roleId);

    @Query("select action.id from RoleAction where role.id = :roleId")
    List<Integer> listActionIdsByRoleId(@Param("roleId") int roleId);

    @Query("select action.actionType from RoleAction where role.id = :roleId")
    List<SysRoleAction.ActionType> listActionTypesByRoleId(@Param("roleId") int roleId);
}
