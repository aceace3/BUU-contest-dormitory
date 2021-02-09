package com.dormitory.service.account.impl;

import com.dormitory.dao.account.SignLogRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.log.SignLog;
import com.dormitory.service.account.SignLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/5/19 18:30
 */
@Service
@Transactional
public class SignLogServiceImpl implements SignLogService {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    SignLogRepository signLogRepository;

    @Override
    public Map getSignLog(HttpServletRequest request, List<SignLog> signLogs) {
        Map resMap=new HashMap();
        List signLogDate=new ArrayList();

        int daysCount=0;
        String signLogDays="";

        for(SignLog signLog: signLogs){
            if (signLog.getStudentInfo().getId()==getSvo(request).getRealId()) {
                daysCount++;
                signLogDays= String.valueOf(signLog.getClockInYear())+String.valueOf(signLog.getClockInMonth())+String.valueOf(signLog.getClockInDay());
                signLogDate.add(signLogDays);
            }
        }
        resMap.put("sumDays",daysCount);
        resMap.put("signLogDate",signLogDate);
        return resMap;
    }
    public SessionVO getSvo(HttpServletRequest request) {
        return (SessionVO) request.getSession().getAttribute(SESSION_VO_STR);
    }
}
