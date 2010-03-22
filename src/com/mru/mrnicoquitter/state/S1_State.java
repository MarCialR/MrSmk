package com.mru.mrnicoquitter.state;

import static com.mru.mrnicoquitter.utils.Global.S1_PREFS;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.mru.mrnicoquitter.R;

public class S1_State extends State {

	public S1_State(Context myContext){

		this.myContext= myContext;
		initSuper("S1_State");
		statePreferences = myContext.getSharedPreferences(S1_PREFS, 0);
		String activeStr = statePreferences.getString("active", "");
		if ("".equals(activeStr) || "MainActivity".equals(activeStr)){
			activeStr = "com.mru.mrnicoquitter.DevelopingActivity";
	      SharedPreferences.Editor editor = statePreferences.edit();
	      editor.putString("active", activeStr);
	      
	      editor.commit();
		}
		try {
			active =  Class.forName(activeStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.d("DEBUG", "Creado S1_State y setteado active a "+active.getName());		
		
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
