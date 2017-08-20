package com.fs.po;

public class JYH_Score {
	private int id;
	private int grade;
	private int content_id;
	private int cource_id;
	public JYH_Score() {
		
	}
	
	public JYH_Score(int id, int grade, int content_id, int cource_id) {
		super();
		this.id = id;
		this.grade = grade;
		this.content_id = content_id;
		this.cource_id = cource_id;
	}

	
	@Override
	public String toString() {
		return "Score [id=" + id + ", grade=" + grade + ", content_id=" + content_id + ", cource_id=" + cource_id + "]";
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

	public int getCource_id() {
		return cource_id;
	}

	public void setCource_id(int cource_id) {
		this.cource_id = cource_id;
	}
	
}
