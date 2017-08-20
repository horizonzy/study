package com.fs.po;

public class JYH_Kind {
	private int k_id;
	private String name;

	public JYH_Kind() {
	}

	@Override
	public String toString() {
		return "Kind [k_id=" + k_id + ", name=" + name + "]";
	}

	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
