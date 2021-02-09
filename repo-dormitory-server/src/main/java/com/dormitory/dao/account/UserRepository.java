package com.dormitory.dao.account;

import com.dormitory.dao.BaseRepository;
import com.dormitory.model.account.User;
import com.dormitory.model.info.ClassInfo;
import com.dormitory.model.info.StudentInfo;
import com.dormitory.model.info.TeacherInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {

    @Query("from User u where u.username = :username and remove = 0")
    User findByUsername(@Param("username") String username);

    List<User> findByPhoneIs(String phone);

    User findByStudentInfoIdIs(int studentInfoId);

    User findByStudentInfo(StudentInfo studentInfo);

    @Query("select u.classInfo from User u where u.phone = :phone")
    List<ClassInfo> findClassInfoIdByPhoneIs(@Param("phone") String phone);

    User findByTeacherInfo(TeacherInfo t);

//    String findTeacherNameByTeacherInfoId()

}
