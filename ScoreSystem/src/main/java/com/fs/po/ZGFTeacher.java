package com.fs.po;

public class ZGFTeacher {
	private int id;
	private String name;
	private int process;
	private int num;
	private String pwd;
	
	public ZGFTeacher() {
	
	}

	public ZGFTeacher(int id, String name, int process, int num, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.process = process;
		this.num = num;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", process=" + process + ", num=" + num + ", pwd=" + pwd + "]";
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

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
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
	
	
}
