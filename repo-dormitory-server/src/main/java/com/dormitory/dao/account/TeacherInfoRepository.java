package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.info.TeacherInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherInfoRepository extends BaseRepository<TeacherInfo> {

    @Modifying
    @Transactional
    @Query(value="update teach_Info set board_content = :textInfo where id = :teacherId " , nativeQuery =true)
    void updateTextInfo(@Param("textInfo") String textInfo, @Param("teacherId") int teacherId);

}
