package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.*;
import com.dormitory.model.account.Role;
import com.dormitory.model.account.User;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import com.dormitory.service.account.RegisterService;
import com.dormitory.service.account.impl.RegisterServiceImpl;
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

/**
 * 注册
 *
 * 成功返回true。失败返回false
 */
@RestController
@RequestMapping(value = "/RegisterController", produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class RegisterController {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    RegisterService registerService=new RegisterServiceImpl();

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassInfoRepository classInfoRepository;

    //学生注册
    @RequestMapping(value = "/stuRegister")
    public boolean stuRegister(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){

        logger.info("-= enter stuRegister =-");

        //获取参数
        String stuName=param.getString("stuName");
        int gender=param.getIntValue("gender");
        String studentCode=param.getString("studentCode");
        String phone=param.getString("phone");
//        int classInfoId=param.getIntValue("classInfoId");
        String classInfoName=param.getString("classInfoName");
        String building=param.getString("building");
        int domNumber=param.getIntValue("domNumber");
        int bedNumber=param.getIntValue("bedNumber");
        String password=param.getString("password");
        String confirmPass=param.getString("confirmPass");

        logger.info(stuName+" * "+gender+" * "+studentCode+" * "+phone+" * "+classInfoName+" * "+building+" * "+domNumber+" * "+bedNumber+" * "+password+" * "+confirmPass);
        int classInfoId=classInfoRepository.getClassIDByClassName(classInfoName);


        List<StudentInfo> li=studentInfoRepository.findByStudentCodeIs(studentCode);

        //判断密码与确认密码一致、学号未被注册过。
        if (password.equals(confirmPass) && li.size()==0){
            registerService.studentRegister(stuName,gender,studentCode,phone,classInfoId,building,domNumber,bedNumber,password,confirmPass);
            return true;
        }

        return false;
    }

    //教师注册
    @RequestMapping(value = "/teacherRegister")
    public boolean teacherRegister(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        logger.info("-= enter teacherRegister =-");

        //密钥
        String cipher=param.getString("cipher");
        String teacherName=param.getString("teacherName");
        String phone=param.getString("phone");
        String password=param.getString("password");
        String confirmPass=param.getString("confirmPass");

        List<User> li=userRepository.findByPhoneIs(phone);

        if(password.equals(confirmPass) && cipher.equals("qaz123") && li.size()==0){
            registerService.teacherRegister(teacherName,phone,password,confirmPass);
            return true;
        }

        return false;
    }

}
