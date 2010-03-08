package com.mru.mrnicoquitter.beans;

import java.util.ArrayList;
import java.util.Iterator;

public class Encuesta{
	private ArrayList<EncuestaItem> WIIIIII = new ArrayList<EncuestaItem>();
	public void add ( EncuestaItem it){
		WIIIIII.add(it);
	}
	public Iterator<EncuestaItem> iterator(){
		return WIIIIII.iterator();
	}
	public ArrayList<EncuestaItem> getItems(){
		return WIIIIII;
	}
}