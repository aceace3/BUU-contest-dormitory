package com.dormitory.service.account;

/**
 * @Author: aceace3
 * @Date: 2020/4/23 0023 下午 4:37
 */
public interface RegisterService {

    int studentRegister(String stuName, int gender, String studentCode,
                        String phone, int classInfoId, String building,
                        int domNumber, int bedNumber, String password, String confirmPass);

    int teacherRegister(String teacherName,String phone,String password, String confirmPass);

}
