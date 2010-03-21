package com.mru.mrnicoquitter.state;

import com.mru.mrnicoquitter.R;
import static com.mru.mrnicoquitter.utils.Global.*;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class S1_State extends State {

	public S1_State(Context myContext){
		this.myContext= myContext;
		preferences = myContext.getSharedPreferences(S1_PREFS, 0);
		Log.d("DEBUG", "Creado Q1_State");
	}

	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getLogo() {
		return R.drawable.etapa1;
	}

}
