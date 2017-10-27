package com.briup.exam.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.exam.bean.Subject;
import com.briup.exam.common.exception.ServiceException;
import com.briup.exam.common.util.Criteriable;
import com.briup.exam.common.util.IPageInfo;
import com.briup.exam.dao.SubjectDao;
import com.briup.exam.service.ISubjectService;

@Service
public class ISubjectServiceImpl implements ISubjectService {

	@Autowired
	private SubjectDao dao;

	@Override
	public void registerSubject(Subject subject) throws ServiceException {
		// TODO Auto-generated method stub
		// 判断用户是否存在
		Subject subdao = dao.findSubjectById(subject.getStem());
		if (subdao != null) {
			throw new ServiceException("Subject exist!!!");
		}
		System.out.println("adding");
		dao.save(subject);
	}

/*	public SubjectDao getDao() {
		return dao;
	}
	
	public void setDao(SubjectDao dao) {
		this.dao = dao;
	}*/

	@Override
	public Subject findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findAll(Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findAll(IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findByExample(Subject model, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findByExample(Subject model, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findByCriteria(Criteriable criteriaObj, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findByCriteria(Criteriable criteriaObj, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject findUnique(Subject model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Subject model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchSave(List<Subject> models) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Subject model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Subject model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObject(Subject model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(List<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDelete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchDeleteModel(List<Subject> models) {
		// TODO Auto-generated method stub

	}

}
