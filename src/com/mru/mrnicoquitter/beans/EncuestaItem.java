package com.mru.mrnicoquitter.beans;

import java.util.ArrayList;


public class EncuestaItem{
	
	private final int MULTICHOICE 	= 1;
	private final int TEXT 			= 2; 
	private Integer code;
	private Integer type;
	private String ques;
	private ArrayList<String> answs= new ArrayList<String>();
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public int getType() {
		return type;
	}
	public void setType(String type) {
		
		if (type.equals("multichoice")){
			this.type = MULTICHOICE;
		} else if (type.equals("text")){
			this.type = TEXT;
		} else{
			this.type = MULTICHOICE;
		}
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public ArrayList<String> getAnsws() {
		return answs;
	}
	public void addAnswer(String answer) {
		this.answs.add(answer);
	}
	
}