package com.mru.mrnicoquitter.state;

import com.mru.mrnicoquitter.ui.AppUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public abstract class State {
	public static final String GLOBAL_PREFS = "GLOBAL_PREFS";
	protected SharedPreferences globalPreferences;
	protected SharedPreferences preferences;
	protected Context myContext;

	
	protected void initSuper(){
		if (globalPreferences == null){
			globalPreferences = myContext.getSharedPreferences(GLOBAL_PREFS, 0);

		}
		if (false){
			
			//AppUtils.showDebug(myContext, "DEBUGGING ON");
	      SharedPreferences.Editor editor = globalPreferences.edit();
	      editor.putBoolean("debug", true);

	      // Don't forget to commit your edits!!!
	      editor.commit();
		} else {
		      SharedPreferences.Editor editor = globalPreferences.edit();
		      editor.putBoolean("debug", false);

		      // Don't forget to commit your edits!!!
		      editor.commit();			
		}
	      
		if (!globalPreferences.getBoolean("created",false)){
			//AppUtils.showToastShort(myContext, "creating " + GLOBAL_PREFS);
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

	abstract public Color getColor();
	abstract public int getLogo();

}
