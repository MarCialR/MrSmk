package com.mru.mrnicoquitter.state;

import android.content.Context;

public class StateManagerSGTon {

	public static final String GLOBAL_PREFS = "MyPrefsFile";
	private static State state;
	private static Context myContext;
	
	private StateManagerSGTon() {

		   }

	public static State getState(Context c) {
		if (state == null) {
			state = new TD_State(myContext);
			state.setPreferences(myContext.getSharedPreferences(GLOBAL_PREFS, 0));
			myContext = c;
			return state;
		} else
			return state;
	}

}
