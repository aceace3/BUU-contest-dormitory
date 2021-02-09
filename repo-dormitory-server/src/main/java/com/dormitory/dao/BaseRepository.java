package com.dormitory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础repo继承了jparepo，所有dao层接口均需继承这个接口
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer>,
        JpaSpecificationExecutor<T>, QueryDslPredicateExecutor<T> {

}
