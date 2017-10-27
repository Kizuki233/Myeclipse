package com.briup.exam.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.briup.exam.bean.Department;;

@Repository
public class DepartmentDao extends BaseDao<Serializable> {

	@SuppressWarnings("unchecked")
	public List<Department> findAll(Order...orders){
		Criteria criteria = getSession().createCriteria(Department.class);
		for(Order o:orders)
		  criteria.addOrder(o); 
		return criteria.list();
	}
	// 保存用户
	public void save(Department Department) {
		getSession().save(Department);
	}

	@SuppressWarnings("unchecked")
	public Department findDepartmentByName(String name) {
		System.out.println("finding");
		Criteria criteria = getSession().createCriteria(Department.class);
		List<Department> list = criteria.add(Restrictions.eq("name", name)).list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
