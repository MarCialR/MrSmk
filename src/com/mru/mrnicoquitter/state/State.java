package com.mru.mrnicoquitter.state;

import android.content.SharedPreferences;

public abstract class State {

	protected SharedPreferences preferences;

	public SharedPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}


}
