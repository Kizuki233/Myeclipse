package com.briup.exam.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.briup.exam.bean.Subject;
import com.briup.exam.common.exception.ServiceException;
import com.briup.exam.service.ISubjectService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@Controller
public class SubjectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

 	@Autowired
	private ISubjectService service;

	/**
	 * 回写JSON
	 */
	public void responseJSON(String json) {
		HttpServletResponse response = ServletActionContext.getResponse();
		// response.addHeader("Accept-Control-Allow-Origin",
		// "http://172.18.8.1");
		// http://127.0.0.1
		// 同源策略
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	// 添加用户
	@Action(value="addSubject", results = { @Result(name = "SUCCESS", location = "/tpl/subject/subjectAdd.jsp"),
			@Result(name = "ERROR", location = "/error.jsp") })
	public String addSubject() {
		System.out.println("--addSubject--");
		return "SUCCESS";
	}
 	
	// 保存用户
	@Action(value="saveSubject", results = { @Result(name = "SUCCESS", location = "/index.jsp"),
			@Result(name = "ERROR", location = "/error.jsp") })
	public String saveSubject() {
		System.out.println("--saveSubject--");
		// 保存用户到db
		try {
			service.registerSubject(new Subject("Abfghgc","sdgghgsd"));
			System.out.println("--saveSubject success--");
			return "SUCCESS";
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return "ERROR";
		}
	}
	
	private Subject subject ;
	private String[] choiceContent;
	private String[] choiceCorrect;
	
//	{
//		subject=new Subject();
//		subject.setDepartment(new Department());
//		subject.setSubjectLevel(new SubjectLevel());
//		subject.setTopic(new Topic());
//		subject.setSubjectType(new SubjectType());
//		System.out.println(subject);
//	}
	
	public String[] getChoiceContent() {
		return choiceContent;
	}
	public void setChoiceContent(String[] choiceContent) {
		this.choiceContent = choiceContent;
	}
	public String[] getChoiceCorrect() {
		return choiceCorrect;
	}
	public void setChoiceCorrect(String[] choiceCorrect) {
		this.choiceCorrect = choiceCorrect;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Action(value="/manager/getAllSubjects")
	public String getAllSubjects(){
		List<Subject> subjects=new ArrayList<>();
		Subject subject=new Subject();
		subject.setUploadTime(new Date());
		subject.setId(1L);
		subjects.add(subject);
		responseJSON(JSONArray.fromObject(subjects).toString());
		return NONE;
	}
}
