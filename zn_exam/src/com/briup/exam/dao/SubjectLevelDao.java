package com.briup.exam.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.briup.exam.bean.SubjectLevel;

@Repository
public class SubjectLevelDao extends BaseDao<SubjectLevel> {
	
	public SubjectLevel findById(Long id) {
		return (SubjectLevel) getSession().get(SubjectLevel.class, id);
	}

	public void save(SubjectLevel model) {
		getSession().save(model);
		
	}

	public void saveOrUpdate(SubjectLevel model) {

		getSession().saveOrUpdate(model);
	}

	public void update(SubjectLevel model) {

		getSession().update(model);
	}

	public void deleteObject(SubjectLevel model) {

		getSession().delete(model);
	}

	public void delete(Long id) {
		SubjectLevel subject=new SubjectLevel();
		subject.setId(id);
		getSession().delete(subject);
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectLevel> findObjectAll(Order...orders){
		Criteria criteria  = getSession().createCriteria(SubjectLevel.class);
		if(orders!=null){
			for(Order order:orders){
				criteria.addOrder(order);
			}
		}
		return criteria.list();
	}


}
