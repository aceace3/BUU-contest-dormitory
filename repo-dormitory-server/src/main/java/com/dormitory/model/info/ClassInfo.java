package com.dormitory.model.info;

import com.dormitory.model.AbstractModel;
import com.dormitory.model.account.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "class_info")
public class ClassInfo extends AbstractModel {

    @NotBlank
    private String className;

    @ManyToOne
    private TeacherInfo teacherInfo;



}
