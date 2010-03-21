package com.mru.mrnicoquitter.state;

import android.content.Context;
import android.content.SharedPreferences;
import static com.mru.mrnicoquitter.utils.Global.*;

public class StateManagerSGTon {

	private static State state;
	private static Context myContext;

	private StateManagerSGTon() {

	}

	public static State getState(Context c) {
		if (myContext == null){
			myContext = c;
		}
		if (state == null) {
			SharedPreferences preferences = myContext.getSharedPreferences(TD_PREFS, 0);
			state = new TD_State(myContext);
		}
		return state;
	}
}
