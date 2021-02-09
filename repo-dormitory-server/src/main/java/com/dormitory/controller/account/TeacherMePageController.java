package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.account.User;
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
 * @date 2020/8/17 16:17
 */
@RestController
@Transactional
@RequestMapping(value = "/TeacherMePageController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class TeacherMePageController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    TeacherInfoRepository teacherInfoRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/teacherInfo")
    Map teacherInfo(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        logger.info("teacherInfo=---------------------------------------------------------=");
        Map teacherInfoMap=new HashMap();
        TeacherInfo teaInfo = teacherInfoRepository.getOne(getSvo(request).getRealId());

        User userRepositoryByTeacherInfo = userRepository.findByTeacherInfo(teaInfo);
        logger.info(userRepositoryByTeacherInfo.getUsername()+" "+userRepositoryByTeacherInfo.getRole().getName());
        String teacherName=userRepositoryByTeacherInfo.getUsername();
        String userRole=userRepositoryByTeacherInfo.getRole().getName();
        String boardContent = teaInfo.getBoardContent();

        teacherInfoMap.put("teacherName",teacherName);
        teacherInfoMap.put("userRole",userRole);
        teacherInfoMap.put("boardContent",boardContent);
        return teacherInfoMap;
    }

    @RequestMapping("/updateTextInfo")
    void updateTextInfo(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        String textInfo=param.getString("textInfo");
        teacherInfoRepository.updateTextInfo(textInfo,getSvo(request).getRealId());
        logger.info("update text end...");
    }

    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
