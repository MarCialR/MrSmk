package com.mru.mrnicoquitter.state;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public abstract class State {
	public static final String GLOBAL_PREFS = "GLOBAL_PREFS";
	protected SharedPreferences globalPreferences;
	protected SharedPreferences preferences;
	protected Context myContext;
	protected static final String TD_PREFS = "TD_PREFS";
	protected static final String Q1_PREFS = "Q1_PREFS";
	
	protected void initSuper(){
		if (globalPreferences == null)
			globalPreferences = myContext.getSharedPreferences(GLOBAL_PREFS, 0);
		if (!globalPreferences.getBoolean("created",false)){
			fillStartingGlobalPreferences(globalPreferences);
		}		
	}
	
	private static void fillStartingGlobalPreferences(
			SharedPreferences globalPreferences2) {
		
	      SharedPreferences.Editor editor = globalPreferences2.edit();
	      editor.putBoolean("created", true);

	      // Don't forget to commit your edits!!!
	      editor.commit();
	}

	public SharedPreferences getGlobalPreferences() {
		return globalPreferences;
	}
	
	public SharedPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}

	abstract public  Color getColor();

}
