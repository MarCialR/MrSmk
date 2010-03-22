package com.mru.mrnicoquitter.state;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import static com.mru.mrnicoquitter.utils.Global.*;

public class StateManagerSGTon {

	private static State state;
	private static Context myContext;


	public static State getState(Context c) {
		if (myContext == null){
			myContext = c;
		}
		if (state == null) {
			SharedPreferences global_prefs = myContext.getSharedPreferences(GLOBAL_PREFS, 0);
			
			String stateStr = global_prefs.getString("state", "");
			if ("".equals(stateStr)){
		      SharedPreferences.Editor editor = global_prefs.edit();
		      editor.putString("state", "TD_State");
		      stateStr= "TD_State";
		      editor.commit();
			}
			setState(stateStr);
		}
		return state;
	}
	
	public static void setState(String stateName){
		clearPreferences();
		if (stateName.equals("TD_State"))
			state = new TD_State(myContext);
		else if(stateName.equals("S1_State"))
			state = new S1_State(myContext);
		Log.d("DEBUG", "Cambiado State a: "+stateName);	
		

	    SharedPreferences.Editor editor = state.globalPreferences.edit();
	    editor.putString ("state", stateName);
		editor.commit();	
	}

	private static void clearPreferences() {

	}
}
