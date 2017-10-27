package com.briup.exam.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门
 * */
@Entity
@Table(name="tbl_exam_department")
public class Department implements Serializable {
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;//部门名称
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public Department() {
		super();
	}
	public Department(String name) {
		super();
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
