package com.mru.mrnicoquitter.basura;

public class Cigar {
	private int id;
	private String date;
	private int tipo;
	
	
	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String toSave(){
		return id + "|" + date.toString() + "|" + tipo; 
	}
	
}
