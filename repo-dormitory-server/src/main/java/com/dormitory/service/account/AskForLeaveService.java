package com.dormitory.service.account;

import com.dormitory.model.log.AskForLeave;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/5/19 16:30
 */
public interface AskForLeaveService {

    public List<AskForLeave> getStuRecord(List<AskForLeave> list, HttpServletRequest request);
}
