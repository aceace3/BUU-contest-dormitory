package com.dormitory.model.log;

import com.dormitory.model.AbstractModel;
import com.dormitory.model.info.StudentInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "dom_sign_log")
@Data
@Entity
public class SignLog extends AbstractModel {

    @NotNull
    private int clockInYear;

    @NotNull
    private int clockInMonth;

    @NotNull
    private int ClockInDay;

    @ManyToOne
    private StudentInfo studentInfo;

}
