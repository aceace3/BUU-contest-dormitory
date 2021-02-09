package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.TeacherInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClassInfoRepository extends BaseRepository<ClassInfo> {

    ClassInfo findByClassName(String className);

    @Modifying
    @Transactional
    @Query(value="INSERT into class_Info (class_name,teacher_info_id) values (?1,?2)" , nativeQuery =true)
    Integer addClassByName(String className,int teacherInfo);


    @Transactional
    @Query(value="select id from class_info where class_name = :className" , nativeQuery =true)
    Integer getClassIDByClassName(@Param("className") String className);

    @Transactional
    @Query(value="select class_name from class_info where teacher_info_id = :teacherId" , nativeQuery =true)
    List getClassNameByTeacherId(@Param("teacherId") int teacherId);


}
