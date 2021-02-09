package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.ClassInfoRepository;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.info.ClassInfo;
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

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/7/27 15:19
 */
@RestController
@RequestMapping(value = "/AddClassController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class AddClassController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ClassInfoRepository classInfoRepository;
    

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @RequestMapping("/addClass")
    boolean addClass(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        TeacherInfo teacher=teacherInfoRepository.getOne(getSvo(request).getRealId());
        ClassInfo classInfo = new ClassInfo();
        
        String className=param.getString("className");

        classInfo.setClassName(className);
        classInfo.setTeacherInfo(teacher);
        try {
            classInfoRepository.save(classInfo);
            return true;

        }catch (Exception e) {
            return false;
        }
    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
