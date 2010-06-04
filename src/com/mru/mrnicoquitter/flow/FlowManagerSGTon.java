package com.mru.mrnicoquitter.flow;

import static com.mru.mrnicoquitter.Global.*;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.mru.mrnicoquitter.beans.StageState;
import com.mru.mrnicoquitter.stage.S2_Stage;
import com.mru.mrnicoquitter.stage.Stage;
import com.mru.mrnicoquitter.stage.S1_Stage;
import com.mru.mrnicoquitter.ui.AppUtils;

public class FlowManagerSGTon {

	// ===========================================================
	// Fields
	// ===========================================================	
	
	private static FlowManagerSGTon INSTANCE;
	private static Context myContext;
	private static Stage stage;
	private static SharedPreferences globalPreferences;	
	private static Gson gson;
	
	// ===========================================================
	// 		Constructors & Initialization
	// ===========================================================	
	
	private FlowManagerSGTon() {

		globalPreferences 	= myContext.getSharedPreferences(GLOBAL_PREFS, Context.MODE_PRIVATE);
//		forzarDEBUG();
		gson 				= new Gson();	
	      
		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
			fillStartingGlobalPreferences();
			setStage(S1);
		} else {
			String dehidratedStage = globalPreferences.getString(PREF_ACTUAL_STAGE, null);
		
			if (null != dehidratedStage)
				hidrataStage(dehidratedStage);
			else
				throw new RuntimeException();
		}
		
	}

	private static void fillStartingGlobalPreferences() {
		AppUtils.showToastShort(myContext, "creating " + GLOBAL_PREFS);
		SharedPreferences.Editor editor = globalPreferences.edit();
		editor.putBoolean(PREF_CREATED, true);
		editor.putBoolean(DEBUG, false);
		editor.commit();	      // Don't forget to commit your edits!!!
	}

	// ===========================================================
	// 		GETTERs & SETTERs
	// ===========================================================	
	
	public static FlowManagerSGTon getInstance() {
		return INSTANCE;
	}
	
	static public SharedPreferences getGlobalPreferences() {
		return globalPreferences;
	}	

	public static Stage getStage() {
		return stage;
	}
	
	public static void hidrataStage(String dehidratedStage){
		
		StageState stageState = ((StageState)gson.fromJson(dehidratedStage, StageState.class));
		stage = Stage.initStage(myContext, stageState);
	}

	public static void setStage(StageState stageState){
		
	}
	
	public static void setStage(int stageID){
		clearPreferences();
		switch (stageID){
			case S1:
				stage = new S1_Stage(myContext);
				break;
			case S2:
				stage = new S2_Stage(myContext);
				break;
			case S3:
				stage = new S2_Stage(myContext);
				break;
			case S4:
				stage = new S2_Stage(myContext);
				break;				
		}
		
		Log.d(DEBUG, "Cambiado Stage a: "+ stage.getStageName());	
		String AAAAA = gson.toJson(stage.getStageState());
		globalPreferences.edit().putString (PREF_ACTUAL_STAGE,AAAAA).commit();	
	}

	private static void clearPreferences() {
		// TODO Auto-generated method stub
		
	}

	public static FlowManagerSGTon initManager(Context applicationContext) {
		myContext = applicationContext;
		INSTANCE = new FlowManagerSGTon();
		return INSTANCE;
	}

	// ===========================================================
	// 		UTILITIES
	// ===========================================================	

//	private void forzarDEBUG() {
//	    Editor editor 		= globalPreferences.edit();
//		editor.putBoolean(DEBUG, false);
//		editor.commit();	
//	}
}
