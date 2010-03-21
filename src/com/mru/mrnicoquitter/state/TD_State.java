package com.mru.mrnicoquitter.state;

import com.mru.mrnicoquitter.R;
import static com.mru.mrnicoquitter.utils.Global.*;

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

	@Override
	public int getLogo() {
		return R.drawable.etapa0;
	}
	
}
