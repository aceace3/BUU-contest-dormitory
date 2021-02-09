package com.dormitory.model.account;

import com.dormitory.model.AbstractModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "dom_role_action")
public class RoleAction extends AbstractModel {

    @ManyToOne
    private Role role;

    @ManyToOne
    private SysRoleAction action;

}
