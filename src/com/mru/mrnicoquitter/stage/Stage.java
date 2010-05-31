package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;


public abstract class Stage {
	protected static SharedPreferences globalPreferences;
	protected SharedPreferences stagePreferences;
	protected Context myContext;
	protected String[] subSTGs;
	protected int subSTGsId;		//Identifica el array de Stages
	protected int activeSubSTG;
	Class<?> active;

	
	protected void initSuper(String stageStr){
		if (globalPreferences == null){
			globalPreferences = myContext.getSharedPreferences(GLOBAL_PREFS, 0);

		}
	    Editor editor = globalPreferences.edit();
	    editor.putString (PREF_ACTUAL_STAGE, stageStr);
		editor.putBoolean(DEBUG, false);
		editor.commit();	
	      
		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
			AppUtils.showToastShort(myContext, "creating " + GLOBAL_PREFS);
			fillStartingGlobalPreferences(globalPreferences);
		}		
		initStages();

	}
	
	private void initStages() {
		subSTGs = myContext.getResources().getStringArray(subSTGsId);
	}

	private static void fillStartingGlobalPreferences(SharedPreferences globalPrefs) {
		
	      SharedPreferences.Editor editor = globalPrefs.edit();
	      editor.putBoolean(PREF_CREATED, true);

	      // Don't forget to commit your edits!!!
	      editor.commit();
	}

	public SharedPreferences getGlobalPreferences() {
		return globalPreferences;
	}
	
	public SharedPreferences getPreferences() {
		return stagePreferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.stagePreferences = preferences;
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
	
	public String getInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().toString()).append(NEWLINE);
		sb.append("SUB_STAGES").append(NEWLINE);
		int counter = 0;
		for(String stage:subSTGs){
			if (counter == activeSubSTG)
				sb.append("* - ");
			sb.append(stage.toString()).append(NEWLINE);;
			counter++;
		}
		return sb.toString();
	}


}
