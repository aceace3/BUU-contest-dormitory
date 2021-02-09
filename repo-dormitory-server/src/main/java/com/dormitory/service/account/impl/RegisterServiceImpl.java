package com.dormitory.service.account.impl;

import com.dormitory.dao.account.*;
import com.dormitory.model.account.User;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import com.dormitory.service.BaseServiceImpl;
import com.dormitory.service.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: aceace3
 * @Date: 2020/4/23 0023 下午 5:35
 */
@Service
@Transactional
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService {

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    ClassInfoRepository classInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @Override
    public int studentRegister(String stuName, int gender, String studentCode, String phone, int classInfoId, String building, int domNumber, int bedNumber, String password, String confirmPass) {
        //get对应班级
        ClassInfo classInfo= classInfoRepository.getOne(classInfoId);

        StudentInfo studentInfo=new StudentInfo();
        studentInfo.setGender(gender);
        studentInfo.setStudentCode(studentCode);
        studentInfo.setBuilding(building);
        studentInfo.setDomNumber(domNumber);
        studentInfo.setBedNumber(bedNumber);
        studentInfo.setClassInfo(classInfo);
        studentInfo.setLeaveTimes(0);

        List<StudentInfo> studentInfos=new ArrayList<>();
        studentInfos.add(studentInfo);

        //存入stu_info表
        studentInfoRepository.save(studentInfos);


        User user=new User();
        user.setUsername(stuName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setClassInfo(classInfo);
        user.setStudentInfo(studentInfo);
        user.setRole(roleRepository.findByName("学生"));

        List<User> users=new ArrayList<>();
        users.add(user);

        //存入dom_user表
        userRepository.save(users);


        return 0;
    }

    @Override
    public int teacherRegister(String teacherName,String phone,String password, String confirmPass) {
//        ClassInfo classInfo=classInfoRepository.getOne(1);

        TeacherInfo teacherInfo=new TeacherInfo();
        teacherInfoRepository.save(teacherInfo);

        User user=new User();
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(teacherName);
        user.setRole(roleRepository.findByName("教师"));
        user.setTeacherInfo(teacherInfo);
        userRepository.save(user);

        return 0;
    }
}
