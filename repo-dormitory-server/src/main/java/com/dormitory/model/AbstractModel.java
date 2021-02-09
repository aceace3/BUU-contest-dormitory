package com.dormitory.model;

import com.dormitory.util.CodeUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 文件名称： com.quick.model
 * 初始作者： niecong
 * 创建日期： 2018/1/14
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除
 * ==========================================================
 * 修改记录：
 * 修改作者 日期 修改内容
 * ==========================================================
 * Copyright (c) 2017-2017 .All rights reserved by Kuaifawu.
 */
@Data
@MappedSuperclass
public abstract class AbstractModel {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String uuid;

    @Column
    private boolean remove;

    @Column
    private Date created;

    @Column
    private Date updated;

    @PrePersist
    public void generateCreationTime() {
        if (this.created == null) {
            this.created = new Date();
        }
        this.updated = new Date();
        if (this.uuid == null || this.uuid.trim().isEmpty()) {
            this.uuid = CodeUtil.generateUuid();
        }
    }

    @PreUpdate
    public void saveUpdatedTime() {
        this.updated = new Date();
    }
}
