package com.mru.mrnicoquitter.state;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class Q1_State extends State {

	public Q1_State(Context myContext){
		this.myContext= myContext;
		preferences = myContext.getSharedPreferences(Q1_PREFS, 0);
		Log.d("DEBUG", "Creado Q1_State");
	}

	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
