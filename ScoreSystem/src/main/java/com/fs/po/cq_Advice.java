package com.fs.po;

public class cq_Advice {
	private int id;
	private String summary;
	private String advice;
	private cq_Cource cource_id;
	public cq_Advice(){
		
	}
	@Override
	public String toString() {
		return "Advice [id=" + id + ", summary=" + summary + ", advice=" + advice + ", cource_id=" + cource_id + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public cq_Cource getCource_id() {
		return cource_id;
	}
	public void setCourceid(cq_Cource cource_id) {
		this.cource_id = cource_id;
	}
	
}
