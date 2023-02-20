package com.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.core.util.HibernateUtil.getSessionFactory;

public interface CoreService {

    default Transaction beginTransaction(){
        return getSession().beginTransaction();
    }
    default void commit(){
        getSession().getTransaction().commit();
    }
    default void rollback(){
        getSession().getTransaction().rollback();
    }

    private Session getSession(){
        return getSessionFactory().getCurrentSession();
    }

}