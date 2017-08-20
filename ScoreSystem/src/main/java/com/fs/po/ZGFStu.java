package com.fs.po;

public class ZGFStu {
	private int id;
	private String name;
	private String sex;
	private int num;
	private String pwd;
	private int tclass_id;
	
	public ZGFStu() {
		
	}

	public ZGFStu(int id, String name, String sex, int num, String pwd, int tclass_id) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.num = num;
		this.pwd = pwd;
		this.tclass_id = tclass_id;
	}

	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + ", sex=" + sex + ", num=" + num + ", pwd=" + pwd + ", tclass_id="
				+ tclass_id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getTclass_id() {
		return tclass_id;
	}

	public void setTclass_id(int tclass_id) {
		this.tclass_id = tclass_id;
	}
	
	
}
