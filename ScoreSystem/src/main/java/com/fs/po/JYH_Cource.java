package com.fs.po;

public class JYH_Cource {
    private int cource_id;
	private String cource_name;
	private String date;
	private String teacher_name;

	public JYH_Cource() {

	}

	@Override
	public String toString() {
		return "Cource [cource_id=" + cource_id + ", cource_name=" + cource_name + ", date=" + date + ", teacher_name="
				+ teacher_name + "]";
	}



	public String getCource_name() {
		return cource_name;
	}

	public void setCource_name(String cource_name) {
		this.cource_name = cource_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public int getCource_id() {
		return cource_id;
	}

	public void setCource_id(int cource_id) {
		this.cource_id = cource_id;
	}

}