package com.mru.mrnicoquitter.state;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class TD_State extends State {
	

	
	public TD_State(Context myContext){
		this.myContext= myContext;
		initSuper();
		preferences = myContext.getSharedPreferences(TD_PREFS, 0);
		Log.d("DEBUG", "Creado TD_State");
	}



	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
}