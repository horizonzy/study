package com.fs.po;

public class JYH_Stu {
	private String name;

	public JYH_Stu() {

	}
	

	@Override
	public String toString() {
		return "Stu [name=" + name + "]";
	}


	public JYH_Stu(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
