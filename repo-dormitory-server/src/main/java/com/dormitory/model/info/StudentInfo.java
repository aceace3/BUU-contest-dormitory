package com.dormitory.model.info;

import com.dormitory.model.AbstractModel;
import com.dormitory.model.account.User;
import com.dormitory.model.log.SignLog;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "stu_info")
public class StudentInfo extends AbstractModel {
    @NotNull
    private String studentCode;

    @NotNull
    private String building;

    @NotNull
    private Integer domNumber;

    @NotNull
    private Integer bedNumber;

    @NotNull
    private Integer leaveTimes;

    @NotNull
    private Integer gender;

    @ManyToOne
    private ClassInfo classInfo;

//    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.EAGER)
//    private Set<SignLog> signLogs;

}
