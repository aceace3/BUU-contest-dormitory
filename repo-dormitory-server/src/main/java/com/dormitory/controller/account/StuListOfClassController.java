package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.ClassInfoRepository;
import com.dormitory.dao.account.SignLogRepository;
import com.dormitory.dao.account.StudentInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.account.User;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.log.SignLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * 班级学生列表--teacher
 *
 * @author aceace3
 * @version 1.0
 * @date 2020/7/27 9:15
 */
@RestController
@RequestMapping(value = "/StuListOfClassController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class StuListOfClassController {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ClassInfoRepository classInfoRepository;

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SignLogRepository signLogRepository;

    @RequestMapping("/stuList")
    List<StudentInfo> stuList(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        String className=param.getString("className");
        logger.info("className: "+className);
        List<StudentInfo> stuLi = studentInfoRepository.findByClassInfo(classInfoRepository.findByClassName(className));
        String sex="";
        List stuReturnLi=new ArrayList();
        for(int i=0;i<stuLi.size();i++){
            List stu=new ArrayList();
            User student=userRepository.findByStudentInfo(stuLi.get(i));
            String stuName=student.getUsername();
            Integer gender=stuLi.get(i).getGender();
            if (gender==1) sex="男";
            else if(gender==0) sex="女";
            String studentCode=stuLi.get(i).getStudentCode();
            int signLogDays=signLogRepository.signLogDays();
            stu.add(stuName);
            stu.add(sex);
            stu.add(studentCode);
            stu.add(signLogDays);
            stuReturnLi.add(stu);
        }
        
        return stuReturnLi;
    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }


}
