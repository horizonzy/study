package com.fs.po;

public class lx_Score {
	private int id ;
	private int grade;
	private int content_id;
	private int stu_id;
	public lx_Score() {
		
	}

	public lx_Score(int id, int grade, int content_id,int stu_id) {
		super();
		this.id = id;
		this.grade = grade;
		this.content_id = content_id;
		this.stu_id=stu_id;
	}

	

	@Override
	public String toString() {
		return "Score [id=" + id + ", grade=" + grade + ", content_id=" + content_id + ", stu_id=" + stu_id + "]";
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

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	
	
}
