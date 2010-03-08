package com.mru.mrnicoquitter.beans;

import java.util.Date;

import android.content.Context;

public class Cigar implements Comparable<Cigar>{
	private int id;
	private String dateStr;
	private int tipo;
	private Date date;
	
	public Cigar() {
		// TODO Auto-generated constructor stub
	}
	
	public Cigar(Context context) {
		// TODO Auto-generated constructor stub
	}
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

	public Date getDate() {
		return date;
	}

	@Override
	public int compareTo(Cigar that) {
		return this.getDate().compareTo(that.getDate());
	}

}
