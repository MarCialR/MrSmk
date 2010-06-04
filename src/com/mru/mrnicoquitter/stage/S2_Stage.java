package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;
import static com.mru.mrnicoquitter.Global.S2_PREFS;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.util.Log;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.StageState;


public class S2_Stage extends Stage {

	public S2_Stage(Context _context){

		myContext			= _context;
		logoId 				= R.drawable.etapa1;
		subSTGsId 			= R.array.S2_Stages_Descriptions;
		stagePreferences 	= myContext.getSharedPreferences(S2_PREFS, 0);
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
	public S2_Stage(Context _context, StageState _state) {

		myContext				= _context;
		logoId					= _state.getLogoId();
		activeSubSTG			= _state.getActiveSubSTG();
		subSTGs					= _state.getSubSTGs();
		stageID					= _state.getStageID();
		stagePreferencesName	= _state.getStagePreferencesName();
	}

}
