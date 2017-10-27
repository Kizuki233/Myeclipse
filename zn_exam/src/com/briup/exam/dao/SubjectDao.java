package com.briup.exam.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.briup.exam.bean.Subject;;

@Repository
public class SubjectDao extends BaseDao<Subject> {
	
	public Subject findById(Long id) {
		return (Subject) getSession().get(Subject.class, id);
	}

	public void save(Subject model) {
		getSession().save(model);
		
	}

	public void saveOrUpdate(Subject model) {
		getSession().saveOrUpdate(model);
	}

	public void update(Subject model) {
		getSession().update(model);
	}

	public void deleteObject(Subject model) {
		getSession().delete(model);
	}

	public void delete(Long id) {
		Subject subject=new Subject();
		subject.setId(id);
		getSession().delete(subject);
	}
    //查找在不在
	public Subject findSubjectById(String stem) {
		System.out.println("finding");
		Criteria criteria = getSession().createCriteria(Subject.class);
		@SuppressWarnings("unchecked")
		List<Subject> list = criteria.add(Restrictions.eq("stem", stem)).list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
