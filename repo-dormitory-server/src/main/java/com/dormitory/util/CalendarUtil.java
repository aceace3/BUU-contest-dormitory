package com.dormitory.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: aceace3
 * @Date: 2020/4/27 0027 上午 12:47
 */
public class CalendarUtil {
//    判断是否在规定的时间内签到 nowTime 当前时间 beginTime规定开始时间 endTime规定结束时间
    public static boolean timeCalendar(Date nowTime, Date pmBeginTime, Date pmEndTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar pmBegin = Calendar.getInstance();
        pmBegin.setTime(pmBeginTime);//下午开始时间
        //设置结束时间
        Calendar pmEnd = Calendar.getInstance();
        pmEnd.setTime(pmEndTime);//下午结束时间
        //处于开始时间之后，和结束时间之前的判断
        if (date.after(pmBegin) && date.before(pmEnd)) {
            return true;
        } else {
            return false;
        }
    }
}
