package com.briup.exam.service;

import com.briup.exam.bean.Department;
import com.briup.exam.common.exception.ServiceException;

public interface IDepartmentService extends IBaseService<Department> {

	void registerDepartment(Department department) throws ServiceException;

}
