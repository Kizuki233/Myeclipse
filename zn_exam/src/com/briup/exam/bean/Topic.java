package com.briup.exam.bean;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 知识点
 * */
@Entity
@Table(name="tbl_exam_topic")
public class Topic implements Serializable {
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + ", department=" + department + "]";
	}
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;//知识点名称
	private Department department; //所属方向
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
