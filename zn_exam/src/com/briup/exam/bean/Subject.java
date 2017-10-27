package com.briup.exam.bean;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 题目表
 * */
@Entity
@Table(name="tbl_exam_subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	//题干
	private String stem;
	//上传时间
	private Date uploadTime;
	public Subject(String stem, String answer) {
		super();
		this.stem = stem;
		this.answer = answer;
	}
	public Subject() {
		super();
	}
	//答案
	private String answer;//问答题答案
	//解析
	private String analysis;
	//审核状态
	private String checkState;
	
	
	//上传者
	private User user;
	//所属试卷
	//题目
	private Set<PaperSubject> paperSubjects = new HashSet<PaperSubject>();
		
	
	//所属方向
	private Department department;
	//题目类型
	private SubjectType subjectType;
	//题目难易程度
	private SubjectLevel subjectLevel;
	//考核知识点
	private Topic topic;
	//正确的选项
	private Set<Choice> choices = new HashSet<Choice>();
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStem() {
		return stem;
	}
	public void setStem(String stem) {
		this.stem = stem;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/**
	 * 一对多
	 * */
	@OneToMany
	@JoinColumn(name="subject_id")
	@Cascade({CascadeType.REMOVE})
	public Set<PaperSubject> getPaperSubjects() {
		return paperSubjects;
	}
	public void setPaperSubjects(Set<PaperSubject> paperSubjects) {
		this.paperSubjects = paperSubjects;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="topic_id")
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	@OneToMany
	@JoinColumn(name="subject_id")
	@Cascade({CascadeType.REMOVE})
	public Set<Choice> getChoices() {
		return choices;
	}
	public void setChoices(Set<Choice> choices) {
		this.choices = choices;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subjectType_id")
	public SubjectType getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subjectLevel_id")
	public SubjectLevel getSubjectLevel() {
		return subjectLevel;
	}
	public void setSubjectLevel(SubjectLevel subjectLevel) {
		this.subjectLevel = subjectLevel;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", stem=" + stem + ", uploadTime=" + uploadTime + ", answer=" + answer
				+ ", analysis=" + analysis + ", user=" + user + ", examPaper=" + null + ", department="
				+ department + ", subjectType=" + subjectType + ", subjectLevel=" + subjectLevel + ", topic=" + topic
				+ "]";
	}
}
