package com.core.dao;


import com.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface CoreDao<P, I> {
	default List<P> getlist() {
		List<P> list=new ArrayList<P>();
		return list;
	};

	void insert(P pojo);

	void deleteById(I id);


	void update(P pojo);

	P getById(I id);

	List<P> getAll();



	default Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

}
