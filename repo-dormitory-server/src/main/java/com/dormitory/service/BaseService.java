package com.dormitory.service;

public interface BaseService {

    void detach(Object o);

    void flush();

    void clear();

    void clear(Object o);

    void add(Object o);

    void update(Object o);

    void remove(Object o);

    <T> T findById(Class<T> clazz, int id);

    <T> T findByUuid(Class<T> clazz, String uuid);
}
