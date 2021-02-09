package com.dormitory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("BaseService")
@Transactional
public class BaseServiceImpl implements BaseService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void clear() {
        em.clear();
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public void clear(Object ob) {
        em.detach(ob);
    }

    @Override
    public void detach(Object o) {
        em.detach(o);
    }

    @Override
    public void add(Object obj) {
        em.persist(obj);
    }

    @Override
    public void update(Object t) {
        em.merge(t);
    }

    @Override
    public void remove(Object t) {
        em.remove(t);
    }

    @Override
    public <T> T findById(Class<T> clazz, int id) {
        return em.find(clazz, id);
    }

    @Override
    public <T> T findByUuid(Class<T> clazz, String uuid) {
        String hqlString = "from " + clazz.getSimpleName() + " where remove = false and uuid = :uuid";
        Query q = em.createQuery(hqlString);
        q.setParameter("uuid", uuid);
        q.setMaxResults(1);
        List<T> list = q.getResultList();
        if (list.size() > 0) return list.get(0);
        else return null;
    }
}
