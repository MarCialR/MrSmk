package com.mru.mrnicoquitter.state;

import static com.mru.mrnicoquitter.Global.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mru.mrnicoquitter.R;

public abstract class State {
	protected SharedPreferences globalPreferences;
	protected SharedPreferences statePreferences;
	protected Context myContext;
	Class<?> active;

	
	protected void initSuper(String stateStr){
		if (globalPreferences == null){
			globalPreferences = myContext.getSharedPreferences(GLOBAL_PREFS, 0);

		}
	    SharedPreferences.Editor editor = globalPreferences.edit();
	    editor.putString (PREF_ACTUAL_STATE, stateStr);
		editor.putBoolean(DEBUG, false);
		editor.commit();	
	      
		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
			//AppUtils.showToastShort(myContext, "creating " + GLOBAL_PREFS);
			fillStartingGlobalPreferences(globalPreferences);
		}		

	}
	
	private static void fillStartingGlobalPreferences(
			SharedPreferences globalPreferences2) {
		
//	      SharedPreferences.Editor editor = globalPreferences2.edit();
//	      editor.putBoolean("created", true);
//
//	      // Don't forget to commit your edits!!!
//	      editor.commit();
	}

	public SharedPreferences getGlobalPreferences() {
		return globalPreferences;
	}
	
	public SharedPreferences getPreferences() {
		return statePreferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.statePreferences = preferences;
	}

	abstract public Color getColor();
	abstract public int getLogo();
	public View getCommonLayout(LayoutInflater inflater, int contentLayout){

		LinearLayout commonLyt = (LinearLayout)inflater.inflate(R.layout.lay_common, null);
		inflater.inflate(contentLayout, commonLyt,true);
		
		ImageView logo = (ImageView)  commonLyt.findViewById(R.id.Logo);
		logo.setBackgroundResource(getLogo());
		return commonLyt;
	}

	
	public Class<?> getActivity() {
		return active;
	}


}
