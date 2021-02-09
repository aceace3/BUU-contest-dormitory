package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.SignLogRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.log.AskForLeave;
import com.dormitory.model.log.SignLog;
import com.dormitory.service.account.SignLogService;
import com.dormitory.service.account.impl.SignLogServiceImpl;
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
import java.util.Map;

import static com.dormitory.util.CodeConst.SESSION_VO_STR;

/**
 *
 * 日历功能
 *
 * @author aceace3
 * @version 1.0
 * @date 2020/5/19 17:45
 */
@RestController
@RequestMapping(value = "/SignLogController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class SignLogController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    SignLogRepository signLogRepository;

    @Autowired
    SignLogService signLogService;

    @RequestMapping(value = "/getSignLog")
    public Map getSignLog(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){

        logger.info("-= enter getSignLog =-");

        List<SignLog> signLogs=signLogRepository.findAll();
        Map allSignLog=signLogService.getSignLog(request,signLogs);

        return allSignLog;
    }

}
