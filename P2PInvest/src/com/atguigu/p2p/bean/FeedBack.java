package com.atguigu.p2p.bean;

public class FeedBack {
	private int id;
	private String department;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public FeedBack(int id, String department, String content) {
		super();
		this.id = id;
		this.department = department;
		this.content = content;
	}
	public FeedBack() {
		super();
	}
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", department=" + department
				+ ", content=" + content + "]";
	}
	
}
