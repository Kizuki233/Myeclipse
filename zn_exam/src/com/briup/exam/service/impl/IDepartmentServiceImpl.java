package com.briup.exam.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.exam.bean.Department;
import com.briup.exam.common.exception.ServiceException;
import com.briup.exam.common.util.Criteriable;
import com.briup.exam.common.util.IPageInfo;
import com.briup.exam.dao.DepartmentDao;
import com.briup.exam.service.IDepartmentService;

@Service
public class IDepartmentServiceImpl implements IDepartmentService {
	@Autowired
	private DepartmentDao dao;
	
/*	private Department depart;
	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}*/

	@Override
	public void registerDepartment(Department Department) throws ServiceException {
		// TODO Auto-generated method stub
		// 判断用户是否存在
		Department depart = dao.findDepartmentByName(Department.getName());
		if (depart != null) {
			throw new ServiceException("Department exist!!!");
		}
		System.out.println("adding");
		dao.save(Department);
	}



	@Override
	public Department findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll(Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll(IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByExample(Department model, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByExample(Department model, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByCriteria(Criteriable criteriaObj, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByCriteria(Criteriable criteriaObj, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department findUnique(Department model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Department model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchSave(List<Department> models) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Department model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObject(Department model) {
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
	public void batchDeleteModel(List<Department> models) {
		// TODO Auto-generated method stub

	}

}
