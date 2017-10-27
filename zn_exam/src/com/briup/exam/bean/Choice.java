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
 * 题目选项
 * */
@Entity
@Table(name="tbl_exam_choice")
public class Choice implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	//选项内容
	private String content;
	private Boolean correct;//是否正确
	//所属题目
	private Subject subject;

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Choice [id=" + id + ", content=" + content + ", correct=" + correct + ", subject=" + subject + "]";
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subject_id")
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Boolean getCorrect() {
		return correct;
	}
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	@Override
	public int hashCode() {
		return id.intValue();
	}
	@Override
	public boolean equals(Object obj) {
		Choice c = (Choice)obj;
		return c.getId()==this.id;
	}
}
