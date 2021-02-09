package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.account.Role;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentInfoRepository extends BaseRepository<StudentInfo> {


    List<StudentInfo> findByStudentCodeIs(String studentCode);

    List<StudentInfo> findByClassInfo(ClassInfo classInfo);


}
