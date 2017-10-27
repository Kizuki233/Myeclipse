package com.briup.exam.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.briup.exam.bean.Department;
import com.briup.exam.bean.SubjectLevel;
import com.briup.exam.bean.SubjectType;
import com.briup.exam.bean.Topic;
import com.briup.exam.service.impl.IDepartmentServiceImpl;
import com.briup.exam.service.impl.ISubjectLevelServiceImpl;
import com.briup.exam.service.impl.ISubjectTypeServiceImpl;
import com.briup.exam.service.impl.ITopicServiceImpl;

/**
 * 当应用启动时，加载所有的subjectLevel subjectType department topic 到application中
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ApplicationListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("context init");
		ServletContext ap = arg0.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ap);
		
		ISubjectTypeServiceImpl service1 = (ISubjectTypeServiceImpl) ac.getBean("ISubjectTypeServiceImpl");
		ISubjectLevelServiceImpl service2 = (ISubjectLevelServiceImpl) ac.getBean("ISubjectLevelServiceImpl");
		IDepartmentServiceImpl service3 = (IDepartmentServiceImpl) ac.getBean("IDepartmentServiceImpl");
		ITopicServiceImpl service4 = (ITopicServiceImpl) ac.getBean("ITopicServiceImpl");
		
		List<SubjectType> subjecttype = service1.findAll(Order.desc("id"));
		List<SubjectLevel> subjectlevel = service2.findAll(Order.desc("id"));
		List<Department> department = service3.findAll(Order.desc("id"));
		List<Topic> topic = service4.findAll(Order.desc("id"));

		ap.setAttribute("ubjectLevel", subjectlevel);
		ap.setAttribute("subjectType", subjecttype);
		ap.setAttribute("department", department);
		ap.setAttribute("topic", topic);
		
		for (SubjectLevel a : subjectlevel) {
			System.out.println(a);
		}
	}
}
