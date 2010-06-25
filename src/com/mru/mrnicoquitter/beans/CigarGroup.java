package com.mru.mrnicoquitter.beans;

import java.util.Date;

public class CigarGroup {
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
<<<<<<< HEAD:src/com/mru/mrnicoquitter/beans/CigarGroup.java
	public Date getDate() {
		return date;
	}
	
=======
>>>>>>> origin/dev:src/com/mru/mrnicoquitter/beans/CigarGroup.java
	
	
}
