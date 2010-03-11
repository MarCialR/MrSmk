package com.mru.mrnicoquitter.state;

import android.content.Context;

public class StateManagerSGTon {

	private static State state;
	private static Context myContext;

	private StateManagerSGTon() {

	}

	public static State getState(Context c) {
		if (c != null){
			myContext = c;
		}
		if (state == null) {
			state = new TD_State(myContext);
		}
		return state;
	}
}
