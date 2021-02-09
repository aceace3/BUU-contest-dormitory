package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.ClassInfoRepository;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.account.User;
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
import java.util.ArrayList;
import java.util.List;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/7/23 15:32
 */
@RestController
@Transactional
@RequestMapping(value = "/MyClassPageController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class MyClassPageController {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @Autowired
    ClassInfoRepository classInfoRepository;

//    @RequestMapping(value = "/findClassInfoIdByPhone")
//    List<String> findClassInfoIdByPhone(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
//
//        String phone =userRepository.findByUsername(getSvo(request).getSvoUsername()).getPhone();
//        List<ClassInfo> classInfo = userRepository.findClassInfoIdByPhoneIs(phone);
//        List<String> className=new ArrayList<>();
//        for (int i=0;i<classInfo.size();i++){
//            className.add(classInfo.get(i).getClassName());
//        }
//
//        return className;
//    }
    @RequestMapping(value = "/getClassNameList")
    List<String> getClassNameList(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){

        List<String> className=null;
        int teacherId = getSvo(request).getRealId();
        className=classInfoRepository.getClassNameByTeacherId(teacherId);


        return className;
    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
