package com.briup.exam.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.briup.exam.bean.Subject;
import com.briup.exam.bean.User;

@Repository
public class UserDao extends BaseDao<User> {

	// 保存用户
	public void save(Subject subject) {
		getSession().save(subject);
	}

	@SuppressWarnings("unchecked")
	public User findUserByName(String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		List<User> list = criteria.add(Restrictions.eq("name", name)).list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
