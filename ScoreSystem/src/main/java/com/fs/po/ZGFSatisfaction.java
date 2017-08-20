package com.fs.po;

public class ZGFSatisfaction {
	private int id;
	private int satisfaction_1;
	private int satisfaction_2;
	private int satisfaction_3;
	private int satisfaction_4;
	private int satisfaction_5;
	
	public ZGFSatisfaction() {
		
	}

	public ZGFSatisfaction(int id, int satisfaction_1, int satisfaction_2, int satisfaction_3, int satisfaction_4,
			int satisfaction_5) {
		super();
		this.id = id;
		this.satisfaction_1 = satisfaction_1;
		this.satisfaction_2 = satisfaction_2;
		this.satisfaction_3 = satisfaction_3;
		this.satisfaction_4 = satisfaction_4;
		this.satisfaction_5 = satisfaction_5;
	}

	@Override
	public String toString() {
		return "Satisfaction [id=" + id + ", satisfaction_1=" + satisfaction_1 + ", satisfaction_2=" + satisfaction_2
				+ ", satisfaction_3=" + satisfaction_3 + ", satisfaction_4=" + satisfaction_4 + ", satisfaction_5="
				+ satisfaction_5 + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSatisfaction_1() {
		return satisfaction_1;
	}

	public void setSatisfaction_1(int satisfaction_1) {
		this.satisfaction_1 = satisfaction_1;
	}

	public int getSatisfaction_2() {
		return satisfaction_2;
	}

	public void setSatisfaction_2(int satisfaction_2) {
		this.satisfaction_2 = satisfaction_2;
	}

	public int getSatisfaction_3() {
		return satisfaction_3;
	}

	public void setSatisfaction_3(int satisfaction_3) {
		this.satisfaction_3 = satisfaction_3;
	}

	public int getSatisfaction_4() {
		return satisfaction_4;
	}

	public void setSatisfaction_4(int satisfaction_4) {
		this.satisfaction_4 = satisfaction_4;
	}

	public int getSatisfaction_5() {
		return satisfaction_5;
	}

	public void setSatisfaction_5(int satisfaction_5) {
		this.satisfaction_5 = satisfaction_5;
	}
	
	
}
