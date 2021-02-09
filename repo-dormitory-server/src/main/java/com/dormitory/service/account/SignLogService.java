package com.dormitory.service.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.model.log.SignLog;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author aceace3
 * @version 1.0
 * @date 2020/5/19 18:27
 */
public interface SignLogService {
    public Map getSignLog(HttpServletRequest request, List<SignLog> signLogs);
}
