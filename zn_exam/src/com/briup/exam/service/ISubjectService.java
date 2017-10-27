package com.briup.exam.service;

import com.briup.exam.bean.Subject;
import com.briup.exam.common.exception.ServiceException;

public interface ISubjectService extends IBaseService<Subject> {
	
	public void registerSubject(Subject subject)
			throws ServiceException;

}
