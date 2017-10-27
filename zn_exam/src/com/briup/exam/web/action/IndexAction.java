package com.briup.exam.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

//action
@Controller
public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "/toIndex", results = { @Result(name = "SUCCESS", 
			location = "/WEB-INF/jsp/test.jsp") })
	public String toIndex() {
		System.out.println("in to index..");
		return "SUCCESS";
	}
}
