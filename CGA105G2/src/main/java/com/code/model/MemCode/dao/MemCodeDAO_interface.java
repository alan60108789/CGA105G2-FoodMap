package com.code.model.MemCode.dao;

import com.code.model.MemCode.pojo.MemCode;
import com.core.dao.CoreDao;
import org.hibernate.Session;

import java.util.List;

public interface MemCodeDAO_interface extends CoreDao<MemCode,Integer> {
    @Override
    void insert(MemCode pojo);


    void delete(MemCode pojo);

    @Override
    void update(MemCode pojo);

    void update(MemCode pojo1, MemCode pojo2);

    @Override
    MemCode getById(Integer id);


    List<MemCode> getByPK(Integer id, String pk);

    @Override
    List<MemCode> getAll();

    @Override
    default Session getSession() {
        return CoreDao.super.getSession();
    }
}
