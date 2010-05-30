package com.mru.mrnicoquitter.state;

import static com.mru.mrnicoquitter.Global.*;
import static com.mru.mrnicoquitter.Global.S1_PREFS;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.mru.mrnicoquitter.R;


public class S1_State extends State {

	public S1_State(Context myContext){

		this.myContext= myContext;
		initSuper(S1_STATE);
		statePreferences = myContext.getSharedPreferences(S1_PREFS, 0);
		String activeStr = statePreferences.getString(PREF_ACTIVE_ACVTY, EMPTY);
		if (EMPTY.equals(activeStr) || "MainActivity".equals(activeStr)){
			activeStr = ACVTY_DEVELOPING_CLASS ;
			SharedPreferences.Editor editor = statePreferences.edit();
			editor.putString(PREF_ACTIVE_ACVTY, activeStr);
			editor.commit();
		}
		try {
			active =  Class.forName(activeStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.d(DEBUG, "Creado S1_State y setteado active a "+active.getName());		
		
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
