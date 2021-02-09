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
@Table(name = "dom_permission")
public class Permission extends AbstractModel {

    @NotNull
    private String name;

    /**
     * 多少用户在用这个权限
     */
    @Transient
    private Integer useNum;

    /**
     * 描述
     */
    @Column(columnDefinition = "varchar(1000)")
    private String description;

    /**
     * 可查看的渠道类型；1：加盟;2：快法务自营(不会展示和使用);3:快合自营;4:第三方;0:全部
     */
    private Integer type;

}
