package com.briup.exam.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.briup.exam.bean.SubjectType;

@Repository
public class SubjectTypeDao extends BaseDao<SubjectType> {
	public SubjectType findById(Long id) {
		return (SubjectType) getSession().get(SubjectType.class, id);
	}

	public void save(SubjectType model) {
		getSession().save(model);
	}

	public void saveOrUpdate(SubjectType model) {
		getSession().saveOrUpdate(model);
	}

	public void update(SubjectType model) {
		getSession().update(model);
	}

	public void deleteObject(SubjectType model) {

		getSession().delete(model);
	}

	public void delete(Long id) {
		SubjectType subject=new SubjectType();
		subject.setId(id);
		getSession().delete(subject);
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectType> findObjectAll(Order...orders){
		Criteria criteria  = getSession().createCriteria(SubjectType.class);
		if(orders!=null){
			for(Order order:orders){
				criteria.addOrder(order);
			}
		}
		return criteria.list();
	}


}
