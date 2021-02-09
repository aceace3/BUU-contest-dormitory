package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.ClassInfoRepository;
import com.dormitory.dao.account.StudentInfoRepository;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.account.User;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/5/27 15:52
 */
@RestController
@RequestMapping(value = "/StuMinePageController", produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
@Transactional
public class StuMinePageController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @Autowired
    ClassInfoRepository classInfoRepository;

    @RequestMapping("getStuMine")
    public Map getStuMine(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){

        logger.info("-= enter getStuMine =-");

        Map<String,String> stuMineMap=new HashMap<>();

        StudentInfo studentInfo = studentInfoRepository.getOne(getSvo(request).getRealId());
        TeacherInfo teacherInfo = classInfoRepository.getOne(studentInfo.getClassInfo().getId()).getTeacherInfo();
        User byStudentInfo = userRepository.findByStudentInfoIdIs(studentInfo.getId());

        String content=teacherInfo.getBoardContent();
        String username=byStudentInfo.getUsername();
        String studentCode=studentInfo.getStudentCode();
        String className=studentInfo.getClassInfo().getClassName();
        String dormNumber=studentInfo.getBuilding()+"-"+studentInfo.getDomNumber()+"-"+studentInfo.getBedNumber();

        stuMineMap.put("content",content);
        stuMineMap.put("username",username);
        stuMineMap.put("studentCode",studentCode);
        stuMineMap.put("className",className);
        stuMineMap.put("dormNumber",dormNumber);

        return stuMineMap;
    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }

}
