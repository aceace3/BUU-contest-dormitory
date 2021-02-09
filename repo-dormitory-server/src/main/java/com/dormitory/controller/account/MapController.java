package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.SignLogRepository;
import com.dormitory.dao.account.StudentInfoRepository;
import com.dormitory.dao.account.UserRepository;
import com.dormitory.model.account.User;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.log.SignLog;
import com.dormitory.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 地图/打卡
 *
 *
 * @Author: aceace3
 * @Date: 2020/4/27 0027 上午 12:47
 */
@RestController
@RequestMapping(value = "/MapController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class MapController {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    SignLogRepository signLogRepository;

    //检查是否在规定时间及规定地点内打卡
    @RequestMapping(value = "/checkClockIn")
    public boolean checkClockIn(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse respons) throws ParseException {

        logger.info("-= enter checkClockIn =-");

        //经度
        double longitude=param.getDoubleValue("longitude");

        //纬度
        double latitude=param.getDoubleValue("latitude");

        //年月日  yyyy/MM/dd
        String localeDate=param.getString("localeDate");


        //经度：110 - 120，纬度：35 - 45， 打卡时间：pmBeginTime - pmEndTime
        if(longitude<=120 && longitude>=110 && latitude<=45 && latitude>=35){
            if (LeaveSignIn()){
                //存表
                int y=Integer.parseInt(localeDate.substring(0,4));
                int m=Integer.parseInt(localeDate.substring(5,7));
                int d=Integer.parseInt(localeDate.substring(8,10));
                System.out.println(y+" "+m+" "+d);

                //needUpdate
                //需改成session获取
                StudentInfo studentInfo=studentInfoRepository.getOne(1);

                SignLog signLog=new SignLog();
                signLog.setStudentInfo(studentInfo);
                signLog.setClockInYear(y);
                signLog.setClockInMonth(m);
                signLog.setClockInDay(d);

                signLogRepository.save(signLog);


                return true;
            }
        }


        return false;
    }

        boolean LeaveSignIn() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式

        Date nowTime =df.parse(df.format(new Date()));

        //下午的规定时间
//        Date pmBeginTime = df.parse("14:00");
            Date pmBeginTime = df.parse("00:00");
            Date pmEndTime = df.parse("24:00");
        //调用判断方法是否在规定时间段内
        boolean isTime = CalendarUtil.timeCalendar(nowTime,pmBeginTime,pmEndTime);

        if(isTime){
            //处于规定的时间段内
            System.out.println("处于规定的时间段内");
            return true;
        }else{
            //不处于规定的时间段内
            System.out.println("不处于规定的时间段内");
            return false;
        }
    }
}
