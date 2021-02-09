package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.log.SignLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SignLogRepository extends BaseRepository<SignLog> {

    @Query("SELECT count(signLog) FROM SignLog signLog GROUP BY signLog.studentInfo")
    int signLogDays();

}
