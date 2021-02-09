package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.AskForLeaveRepository;
import com.dormitory.dao.account.ClassInfoRepository;
import com.dormitory.dao.account.StudentInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.log.AskForLeave;
import io.swagger.models.auth.In;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 *
 * 请假条
 *
 *
 * @author aceace3
 * @version 1.0
 * @date 2020/5/23 17:21
 */
@RestController
@RequestMapping(value = "/AskFoeLeavePaperController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
@Transactional
public class AskFoeLeavePaperController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    AskForLeaveRepository askForLeaveRepository;

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassInfoRepository classInfoRepository;


    @RequestMapping("/addInfo")
    public void addInfo(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        logger.info("-= enter addInfo =-");

        String beginDate=param.getString("beginDate");
        String endDate=param.getString("endDate");

        int beginYear=Integer.parseInt(beginDate.substring(0,4));
        int beginMouth= Integer.parseInt(beginDate.substring(5,7));
        int beginDay= Integer.parseInt(beginDate.substring(8,10));

        int endYear=Integer.parseInt(endDate.substring(0,4));
        int endMouth= Integer.parseInt(endDate.substring(5,7));
        int endDay= Integer.parseInt(endDate.substring(8,10));

        String leaveReasonText=param.getString("leaveReasonText");

        StudentInfo studentInfo = studentInfoRepository.getOne(getSvo(request).getRealId());

        AskForLeave askForLeave=new AskForLeave();
        askForLeave.setBeginDay(beginDay).setBeginMonth(beginMouth).setBeginYear(beginYear);
        askForLeave.setEndDay(endDay).setEndMonth(endMouth).setEndYear(endYear);
        askForLeave.setLeaveReasonText(leaveReasonText);
        askForLeave.setStudentInfo(studentInfo);


        ClassInfo claz=classInfoRepository.getOne(studentInfoRepository.getOne(getSvo(request).getRealId()).getClassInfo().getId());
        askForLeave.setTeacherInfo(claz.getTeacherInfo());


        askForLeaveRepository.save(askForLeave);


    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }

}
