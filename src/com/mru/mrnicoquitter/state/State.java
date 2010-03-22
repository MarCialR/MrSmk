package com.mru.mrnicoquitter.state;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mru.mrnicoquitter.R;
import static com.mru.mrnicoquitter.utils.Global.GLOBAL_PREFS;;

public abstract class State {
	protected SharedPreferences globalPreferences;
	protected SharedPreferences statePreferences;
	protected Context myContext;
	Class active;

	
	protected void initSuper(String stateStr){
		if (globalPreferences == null){
			globalPreferences = myContext.getSharedPreferences(GLOBAL_PREFS, 0);

		}
	    SharedPreferences.Editor editor = globalPreferences.edit();
	    editor.putString ("state", stateStr);
		if (false){
	      editor.putBoolean("debug", true);
		} else {
	      editor.putBoolean("debug", false);
		
		}
		editor.commit();	
	      
		if (!globalPreferences.getBoolean("created",false)){
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

	
	public Class getActivity() {
		return active;
	}


}
