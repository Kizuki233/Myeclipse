package com.briup.exam.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;

import com.briup.exam.bean.Topic;
import com.briup.exam.common.util.Criteriable;
import com.briup.exam.common.util.IPageInfo;
import com.briup.exam.service.ITopicService;

public class ITopicServiceImpl implements ITopicService{

	@Override
	public Topic findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findAll(Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findAll(IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findByExample(Topic model, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findByExample(Topic model, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findByCriteria(Criteriable criteriaObj, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findByCriteria(Criteriable criteriaObj, IPageInfo pageInfo, Order... orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic findUnique(Topic model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Topic model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchSave(List<Topic> models) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Topic model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Topic model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObject(Topic model) {
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
	public void batchDeleteModel(List<Topic> models) {
		// TODO Auto-generated method stub
		
	}

}
