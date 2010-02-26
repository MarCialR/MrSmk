package com.mru.mrnicoquitter.basura;

import java.util.Date;

public class Cigar {
	private int id;
	private String dateStr;
	private int tipo;
	private Date date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String toSave(){
		return id + "|" + dateStr + "|" + tipo; 
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
