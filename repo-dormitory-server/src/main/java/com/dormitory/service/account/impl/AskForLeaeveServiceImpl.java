package com.dormitory.service.account.impl;

import com.dormitory.dao.account.AskForLeaveRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.log.AskForLeave;
import com.dormitory.service.account.AskForLeaveService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/5/19 16:30
 */

@Service
@Transactional
public class AskForLeaeveServiceImpl implements AskForLeaveService {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    AskForLeaveRepository askForLeaveRepository;

    @Autowired
    UserRepository userRepository;

    public List<AskForLeave> getStuRecord(List<AskForLeave> list,HttpServletRequest request){

        List newList =new ArrayList<>();


        for(AskForLeave askForLeave: list){
            if (askForLeave.getStudentInfo().getId()==getSvo(request).getRealId()) {

//                String teacherName=userRepository.findOne(askForLeave.getTeacherInfo().getId()).getUsername();
                String teacherName=userRepository.findByTeacherInfo(askForLeave.getTeacherInfo()).getUsername();
                String replyText=askForLeave.getReplyText();
                int isHandle=askForLeave.getIsHandle();
                String Time=String.valueOf(askForLeave.getUpdated()).substring(5, 16);

                logger.info("teacherName: "+teacherName);


                List teaList =new ArrayList<>();
                teaList.add(teacherName);
                teaList.add(replyText);
                teaList.add(isHandle);
                teaList.add(Time);

                newList.add(teaList);

            }

        }

        return newList;
    }

    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
