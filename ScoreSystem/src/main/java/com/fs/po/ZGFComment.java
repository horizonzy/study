package com.fs.po;

public class ZGFComment {
	private int id;
	private String description;
	private int course_id;
	
	public ZGFComment() {
		
	}

	public ZGFComment(int id, String description, int course_id) {
		super();
		this.id = id;
		this.description = description;
		this.course_id = course_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", description=" + description + ", course_id=" + course_id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
}
