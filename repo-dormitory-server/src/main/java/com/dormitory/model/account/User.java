package com.dormitory.model.account;

import com.dormitory.model.AbstractModel;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "dom_user")
public class User extends AbstractModel {

    @NotBlank
    private String username;

    @NotBlank
    @JsonIgnore
    private String password;

    @NotBlank
    private String phone;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

    @OneToOne
    private StudentInfo studentInfo;

    @OneToOne
    private TeacherInfo teacherInfo;

    @ManyToOne
    private ClassInfo classInfo;
}
