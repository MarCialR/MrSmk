package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;
import static com.mru.mrnicoquitter.Global.S1_PREFS;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.mru.mrnicoquitter.R;


public class S1_Stage extends Stage {

	public S1_Stage(Context myContext){

		this.myContext= myContext;
		subSTGsId = R.array.S1_Stages_Descriptions;
		initSuper(S1_STAGE);
		stagePreferences = myContext.getSharedPreferences(S1_PREFS, 0);
		String activeStr = stagePreferences.getString(PREF_ACTIVE_ACVTY, EMPTY);
		if (EMPTY.equals(activeStr)){
			activeStr = ACVTY_DEVELOPING_CLASS ;
			SharedPreferences.Editor editor = stagePreferences.edit();
			editor.putString(PREF_ACTIVE_ACVTY, activeStr);
			editor.commit();
		}
		try {
			active =  Class.forName(activeStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.d(DEBUG, "Creado S1_Stage y setteado active a "+active.getName());		
		
	}

	
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getLogo() {
		return R.drawable.etapa1;
	}

}
