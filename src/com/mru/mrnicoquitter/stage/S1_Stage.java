package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import android.util.Log;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.StageState;

public class S1_Stage extends Stage {

	
	public S1_Stage(Context _context){
		myContext			= _context;
		logoId 				= R.drawable.etapa0;
		subSTGsId 			= R.array.S1_Stages_Descriptions;
		stagePreferences 	= myContext.getSharedPreferences(S1_PREFS, 0);
		subSTGs 			= myContext.getResources().getStringArray(subSTGsId);
		
		if (activeSubSTG==0){
			Editor editor 	= stagePreferences.edit();
			editor.putString(PREF_ACTIVE_ACVTY, ACVTY_MAIN_CLASS);
			editor.commit();			
			
		}
		String activeStr = stagePreferences.getString(PREF_ACTIVE_ACVTY, EMPTY);

		try {
			activeClassToLaunch =  Class.forName(activeStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.d(DEBUG, "Creado S1_State y setteado active a "+subSTGs[activeSubSTG]);
	}

	public S1_Stage(Context _context, StageState _state) {

		myContext				= _context;
		logoId					= _state.getLogoId();
		activeSubSTG			= _state.getActiveSubSTG();
		subSTGs					= _state.getSubSTGs();
		stageID					= _state.getStageID();
		stagePreferencesName	= _state.getStagePreferencesName();

	}
	
	
	
	
}
