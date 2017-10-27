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
 * 卷子题目
 * */
@Entity
@Table(name="tbl_exam_papersubject")
public class PaperSubject implements Serializable {

	@Override
	public String toString() {
		return "PaperSubject [id=" + id + ", score=" + score + ", examPaper=" + examPaper + ", subject=" + subject
				+ "]";
	}
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer score;
	private ExamPaper examPaper;
	private Subject subject;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="examPaper_id")
	public ExamPaper getExamPaper() {
		return examPaper;
	}
	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subject_id")
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
