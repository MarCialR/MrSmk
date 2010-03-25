package com.mru.mrnicoquitter.encuestas;

import java.util.ArrayList;
import java.util.Iterator;

public class Encuesta implements Iterator<EncuestaItem>{
	String encuestaName;
	private ArrayList<EncuestaItem> WIIIIII = new ArrayList<EncuestaItem>();
	private Iterator<EncuestaItem> iterator;
	public Encuesta(String string) {
		encuestaName = string;
		result = 0;
	}
	public void add ( EncuestaItem it){
		WIIIIII.add(it);
	}
	public Iterator<EncuestaItem> iterator(){
		iterator =WIIIIII.iterator(); 
		return iterator;
	}
	public ArrayList<EncuestaItem> getItems(){
		return WIIIIII;
	}
	public String getEncuestaName() {
		return encuestaName;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}
	@Override
	public EncuestaItem next() {
		// TODO Auto-generated method stub
		return iterator.next();
	}
	@Override
	public void remove() {
		iterator.remove();
	}
	
	private int result;
	
	public int getResult() {
		return result;
	}
	public void sumaResultado(int quantity){
		result = result+quantity;
	}
	

	
}