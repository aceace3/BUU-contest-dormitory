package com.dormitory.model.account;

import com.dormitory.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "dom_role")
public class Role extends AbstractModel {

    @NotNull
    private String name;

    /**
     * 多少用户在用这个角色
     */
    @Transient
    private Integer useNum;

    /**
     * 描述
     */
    @Column(columnDefinition = "varchar(150) COMMENT'备注'")
    private String description;

}
