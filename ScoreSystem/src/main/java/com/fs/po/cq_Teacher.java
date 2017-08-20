package com.fs.po;

public class cq_Teacher {
	private int id;
	private String name;
	private int process;
	private String pwd;
	private String num;
	public cq_Teacher (){
		
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", process=" + process + ", pwd=" + pwd + ", num=" + num + "]";
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
	public void setname(String name) {
		this.name = name;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
