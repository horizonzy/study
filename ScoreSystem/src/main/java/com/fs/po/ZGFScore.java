package com.fs.po;

public class ZGFScore {
	private int id ;
	private int grade;
	private int content_id;
	
	public ZGFScore() {
		
	}

	public ZGFScore(int id, int grade, int content_id) {
		super();
		this.id = id;
		this.grade = grade;
		this.content_id = content_id;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", grade=" + grade + ", content_id=" + content_id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	
	
}
