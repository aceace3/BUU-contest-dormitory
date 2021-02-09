package com.dormitory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 *
 * 定时查询是否打卡并将未打卡学生信息发送至教师
 *
 * @Author: aceace3
 * @Date: 2020/4/27 0027 下午 4:50
 */

public class ScheduleConfig {

        //3.添加定时任务
        //needUpdate  需改成工作日
        //参数：周一至周五的 23:10 触发。 https://blog.csdn.net/u010827544/article/details/88691848
        @Scheduled(cron = "0 10 23 ? * MON-FRI")
        private void isClockIn() {
            System.err.println("23:10了!");
        }

}
