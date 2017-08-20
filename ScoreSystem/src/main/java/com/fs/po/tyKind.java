package com.fs.po;

public class tyKind {
	private int id;
	private String name;
	public tyKind() {
		
	}
	@Override
	public String toString() {
		return "Kind [id=" + id + ", name=" + name + "]";
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
	
}
