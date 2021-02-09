package com.dormitory.model.account;

import com.dormitory.model.AbstractModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dom_sys_role_action")
public class SysRoleAction extends AbstractModel {

    public static enum ActionType {
        //学生api权限
        API_ALLOW_STU,
        //教师api权限
        API_ALLOW_TEACH,
        //管理员api权限
        API_ALLOW_ADMIN
    }

    public static enum ActionGroup {
        //账号组
        API_ACCOUNT
    }

    /**
     * 相关操作
     */
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    /**
     * 分组信息
     */
    @Enumerated(EnumType.STRING)
    private ActionGroup actionGroup;

    @Transient
    private List<SysRoleAction> children;

}
