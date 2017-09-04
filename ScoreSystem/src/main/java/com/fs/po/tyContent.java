package com.fs.po;

public class tyContent {
	private int id;
	private String name;
	private int kind_id;
	private String k_name;
	private String c_name;

	@Override
	public String toString() {
		return "tyContent{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kind_id=" + kind_id +
				", k_name='" + k_name + '\'' +
				", c_name='" + c_name + '\'' +
				'}';
	}

	public tyContent() {
	}

	public tyContent(int id, String name, int kind_id, String k_name, String c_name) {
		this.id = id;
		this.name = name;
		this.kind_id = kind_id;
		this.k_name = k_name;
		this.c_name = c_name;
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

	public int getKind_id() {
		return kind_id;
	}

	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}

	public String getK_name() {
		return k_name;
	}

	public void setK_name(String k_name) {
		this.k_name = k_name;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
}