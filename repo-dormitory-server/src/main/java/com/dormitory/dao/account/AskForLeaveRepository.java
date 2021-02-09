package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.info.TeacherInfo;
import com.dormitory.model.log.AskForLeave;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: aceace3
 * @Date: 2020/4/28 0028 下午 8:48
 */
public interface AskForLeaveRepository extends BaseRepository<AskForLeave> {
    List<AskForLeave> findByTeacherInfo(TeacherInfo teacherInfo);

    @Modifying
    @Transactional
    @Query(value="UPDATE dom_ask_for_leave SET reply_text=?1,updated=?2,is_handle=?3 WHERE id=?4" , nativeQuery =true)
    Integer updateReplyText(String replyText, Date updated, int isHandle, int id);

    Integer countByIsHandleIs(int isHandle);
}
