package com.mru.mrnicoquitter.stage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import static com.mru.mrnicoquitter.Global.*;

public class StageManagerSGTon {

	private static Stage stage;
	private static Context myContext;


	public static Stage getStage(Context c) {
		if (myContext == null){
			myContext = c;
		}
		if (stage == null) {
			SharedPreferences global_prefs = myContext.getSharedPreferences(GLOBAL_PREFS, 0);
			
			String stageStr = global_prefs.getString(STAGE, EMPTY);
			if (EMPTY.equals(stageStr)){
		      Editor editor = global_prefs.edit();
		      editor.putString(STAGE, TD_STAGE);
		      stageStr= TD_STAGE;
		      editor.commit();
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
		

	    Editor editor = Stage.globalPreferences.edit();
	    editor.putString (STAGE, stageName);
		editor.commit();	
	}

	private static void clearPreferences() {

	}
}
