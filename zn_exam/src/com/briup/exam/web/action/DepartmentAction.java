package com.briup.exam.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.exam.bean.Department;
import com.briup.exam.common.exception.ServiceException;
import com.briup.exam.service.IDepartmentService;
import com.briup.exam.web.action.manager.BaseAction;

import net.sf.json.JSONArray;

@Controller
public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

 	@Autowired
	private IDepartmentService service;

	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department dep) {
		this.department = department;
	}

	public String getAllSubjectTypes(){
		List<Department> departments=new ArrayList<>();
		Department department=new Department();
		department.setName("数学");
		department.setId(1L);
		departments.add(department);
		responseJSON(JSONArray.fromObject(departments).toString());
		return NONE;
	}

	// 保存用户
	@Action(value="saveDepartment", results = { @Result(name = "SUCCESS", location = "/success.jsp"),
			@Result(name = "ERROR", location = "/error.jsp") })
	public String addDepartment() {
		System.out.println("--addDepartment--");
		// 保存用户到db
		try {
			service.registerDepartment(department/*new Department("dsgsdg")*/);
			System.out.println("--addDepartment success--");
			return "SUCCESS";
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return "ERROR";
		}
	}
}
