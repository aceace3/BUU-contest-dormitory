package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.AskForLeaveRepository;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.info.TeacherInfo;
import com.dormitory.model.log.AskForLeave;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.sql.Time;
import java.util.*;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 *
 * 请假记录
 *
 * @author aceace3
 * @version 1.0
 * @date 2020/8/15 14:34
 */
@RestController
@RequestMapping(value = "/TeacherListAFLController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class TeacherListAFLController {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    AskForLeaveRepository askForLeaveRepository;
    @Autowired
    TeacherInfoRepository teacherInfoRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/listAskForLeave")
    List<AskForLeave> listAskForLeave(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        List liAFL=new ArrayList<>();
        TeacherInfo teacherInfo = teacherInfoRepository.getOne(getSvo(request).getRealId());
        List<AskForLeave> byTeacherInfo = askForLeaveRepository.findByTeacherInfo(teacherInfo);
        for (int i=0;i<byTeacherInfo.size();i++){
            Map map=new HashMap();
            AskForLeave askForLeave = byTeacherInfo.get(i);
            logger.info(askForLeave);
            String stuName=userRepository.findByStudentInfo(askForLeave.getStudentInfo()).getUsername();
            Integer genderNum=askForLeave.getStudentInfo().getGender();
            String gender=null;
            if (genderNum==1){
                gender="男";
            }else if(genderNum==0){
                gender="女";
            }
            String className=askForLeave.getStudentInfo().getClassInfo().getClassName();
            String aflTime=String.valueOf(askForLeave.getBeginYear())+"-"+String.valueOf(askForLeave.getBeginMonth())+"-"+
                            String.valueOf(askForLeave.getBeginDay())+"~"+String.valueOf(askForLeave.getEndYear())+"-"+String.valueOf(askForLeave.getEndMonth())+
                    "-"+String.valueOf(askForLeave.getEndDay());
            String reason=askForLeave.getLeaveReasonText();
            String reply=askForLeave.getReplyText();
            String replyTime=askForLeave.getUpdated().toString();
            int isHandle=askForLeave.getIsHandle();
            String created = String.valueOf(askForLeave.getCreated());
            int id=askForLeave.getId();

            map.put("id",id);
            map.put("stuName",stuName);
            map.put("gender",gender);
            map.put("className",className);
            map.put("aflTime",aflTime);
            map.put("reason",reason);
            map.put("reply",reply);
            map.put("replyTime",replyTime);
            map.put("isHandle",isHandle);
            map.put("created",created);

            liAFL.add(map);
        }
        return liAFL;
    }

    @RequestMapping("/updateReplyText")
    boolean updateReplyText(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        int id= param.getIntValue("id");
        String replyText=param.getString("replyText");
//        DateTime updated = new DateTime();
        Date updated=new Date();
        int isHandle=param.getIntValue("isHandle");
        Integer integer = askForLeaveRepository.updateReplyText(replyText,updated,isHandle, id);
        if (integer > 0) return true;
        return false;
    }

    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
