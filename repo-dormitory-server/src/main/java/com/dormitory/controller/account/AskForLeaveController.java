package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.AskForLeaveRepository;
import com.dormitory.dao.account.TeacherInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.dto.SessionVO;
import com.dormitory.model.info.TeacherInfo;
import com.dormitory.model.log.AskForLeave;
import com.dormitory.service.account.AskForLeaveService;
import com.dormitory.service.account.impl.AskForLeaeveServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Author: aceace3
 * @Date: 2020/4/28 0028 下午 8:55
 */
@RestController
@RequestMapping(value = "/AskForLeaveController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class AskForLeaveController {
    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    AskForLeaveRepository askForLeaveRepository;


    @Autowired
    AskForLeaveService askForLeaveService=new AskForLeaeveServiceImpl();

    //学生功能: 请假记录
    @RequestMapping(value = "/getStuRecord")
    public List getStuRecord(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){

        logger.info("-= enter getStuRecord=-");
        System.out.println("*********************----------------************************-------------------*********************");

        List<AskForLeave>  askForLeaveList=askForLeaveRepository.findAll();

        List newLi=askForLeaveService.getStuRecord(askForLeaveList,request);



        return newLi;
    }
    @RequestMapping(value = "/getCount")
    int getCount(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons){
        return askForLeaveRepository.countByIsHandleIs(0);
    }


}
