package com.mru.mrnicoquitter.flow;


import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.stage.S1_Stage;
import com.mru.mrnicoquitter.stage.Stage;
import com.mru.mrnicoquitter.stage.TD_Stage;
import com.mru.mrnicoquitter.ui.AppUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.util.Log;

public class FlowManagerSGTon {

	// ===========================================================
	// Fields
	// ===========================================================	
	
	private static FlowManagerSGTon INSTANCE;
	private static Context myContext;
	private static Stage stage;
	private static SharedPreferences globalPreferences;	
	
	// ===========================================================
	// 		Constructors & Initialization
	// ===========================================================	
	
	private FlowManagerSGTon(Context c) {
		if (myContext == null){
			myContext = c;
		}
	    Editor editor = globalPreferences.edit();
	    editor.putString (PREF_ACTUAL_STAGE, stageStr);
		editor.putBoolean(DEBUG, false);
		editor.commit();	
	      
		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
			AppUtils.showToastShort(myContext, "creating " + GLOBAL_PREFS);
			fillStartingGlobalPreferences(globalPreferences);
		}				
	}
	
	private static void fillStartingGlobalPreferences(SharedPreferences globalPrefs) {
		
	      SharedPreferences.Editor editor = globalPrefs.edit();
	      editor.putBoolean(PREF_CREATED, true);
	      editor.commit();	      // Don't forget to commit your edits!!!
	}

	// ===========================================================
	// 		GETTERs & SETTERs
	// ===========================================================	
	
	public static FlowManagerSGTon getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new FlowManagerSGTon(c);
			return INSTANCE;
		} else
			return INSTANCE;
	}
	
	static public SharedPreferences getGlobalPreferences() {
		return globalPreferences;
	}	

	public static Stage getStage(Context c) {
		if (myContext == null){
			myContext = c;
		}
		if (stage == null) {
			SharedPreferences global_prefs = myContext.getSharedPreferences(GLOBAL_PREFS, 0);
			
			String stageStr = global_prefs.getString(PREF_ACTUAL_STAGE, EMPTY);
			if (EMPTY.equals(stageStr)){
				stageStr	= TD_STAGE;
				global_prefs.edit().putString(PREF_ACTUAL_STAGE, stageStr).commit();
			}
			// Zona Limpieza  
			else if(stageStr.equals("S1_State")){
				stageStr	= S1_STAGE;
				global_prefs.edit().putString(PREF_ACTUAL_STAGE, stageStr).commit();	
			}
			else if(stageStr.equals("TD_State")){
				stageStr	= TD_STAGE;
				global_prefs.edit().putString(PREF_ACTUAL_STAGE, stageStr).commit();	
			}			
			setStage(stageStr);
		}
		return stage;
	}
	
	public static void setStage(String stageName){
		clearPreferences();
		if (stageName.equals(TD_STAGE))
			stage = new TD_Stage(myContext);
		else if(stageName.equals(S1_STAGE))
			stage = new S1_Stage(myContext);

		
		Log.d(DEBUG, "Cambiado Stage a: "+stageName);	
		
		globalPreferences.edit().putString (PREF_ACTUAL_STAGE, stageName).commit();
	}

	private static void clearPreferences() {
		// TODO Auto-generated method stub
		
	}


}
