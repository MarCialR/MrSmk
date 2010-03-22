package com.mru.mrnicoquitter.state;

import static com.mru.mrnicoquitter.utils.Global.TD_PREFS;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.mru.mrnicoquitter.R;

public class TD_State extends State {

	
	public TD_State(Context myContext){
		this.myContext= myContext;
		initSuper("TD_State");
		statePreferences = myContext.getSharedPreferences(TD_PREFS, 0);
		String activeStr = statePreferences.getString("active", "");
		if ("".equals(activeStr) || "MainActivity".equals(activeStr)){
	      SharedPreferences.Editor editor = statePreferences.edit();
	      editor.putString("active", "com.mru.mrnicoquitter.MainActivity");
	      editor.commit();
		}
		try {
			active =  Class.forName(activeStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.d("DEBUG", "Creado TD_State y setteado active a "+active.getName());
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
