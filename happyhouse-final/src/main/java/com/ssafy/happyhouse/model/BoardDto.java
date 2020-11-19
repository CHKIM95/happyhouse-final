package com.ssafy.happyhouse.model;

public class BoardDto {
	private int no;
	private String userid;
	private String subject;
	private String content;
	private String regtime;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", userid=" + userid + ", subject=" + subject + ", content=" + content + ", regtime="
				+ regtime + "]";
	}
	public BoardDto(int no, String userid, String subject, String content, String regtime) {
		super();
		this.no = no;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
