package com.dormitory.model.log;

import com.dormitory.model.AbstractModel;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Author: aceace3
 * @Date: 2020/4/28 0028 下午 6:29
 */
@Table(name = "dom_ask_for_leave")
@Data
@Entity
@Accessors(chain = true)
public class AskForLeave extends AbstractModel {

    @ManyToOne
    private StudentInfo studentInfo;

    @ManyToOne
    private TeacherInfo teacherInfo;

    private String leaveReasonText;

    private String replyText;

    private int beginYear;

    private int beginMonth;

    private int beginDay;

    private int endYear;

    private int endMonth;

    private int endDay;

    // 0:未处理 1:批准 2:拒绝
    @Column
    private int isHandle;

}
