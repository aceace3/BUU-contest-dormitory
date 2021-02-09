package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.account.Role;
import com.dormitory.model.account.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role> {

    @Query("from Role r where r.name = :name")
    Role findByName(@Param("name") String name);

}
