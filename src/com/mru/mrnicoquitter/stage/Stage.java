package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;

import java.lang.reflect.Type;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.StageState;

public abstract class Stage {
	// ===========================================================
	// Fields
	// ===========================================================	
	protected int stageID;
	protected int logoId;

	protected SharedPreferences stagePreferences;
	protected String stagePreferencesName;
	protected Context myContext;
	protected int subSTGsId;		//Identifica el array de Stages
	Class<?> activeClassToLaunch;
	protected String[] subSTGs;

	protected int activeSubSTG;		// posicion en el array de Stages ¡¡ De momento son Strings!!	
	
	// ===========================================================
	// 		Constructors & Initialization
	// ===========================================================	

	public static Stage initStage (Context context, StageState state){
		switch (state.getStageID()){
		case S1:
			return new S1_Stage(context,state);
		case S2:
			return new S2_Stage(context,state);

		case S3:
			return new S1_Stage(context,state);

		case S4:
			return new S1_Stage(context,state);
		}
		return new S1_Stage(context,state);
	}

	public SharedPreferences getPreferences() {
		return stagePreferences;
	}
	protected void initCommons(Context _context, StageState _state) {
		myContext				= _context;
		logoId					= _state.getLogoId();
		activeSubSTG			= _state.getActiveSubSTG();
		subSTGs					= _state.getSubSTGs();
		stageID					= _state.getStageID();
		stagePreferencesName	= _state.getStagePreferencesName();
	
	}
	public View getCommonLayout(LayoutInflater inflater, int contentLayout){

		LinearLayout commonLyt = (LinearLayout)inflater.inflate(R.layout.lay_common, null);
		inflater.inflate(contentLayout, commonLyt,true);
		
		ImageView logo = (ImageView) commonLyt.findViewById(R.id.Logo);
		logo.setBackgroundResource(logoId);
		TextView text= (TextView) commonLyt.findViewById(R.id.StageInfo);
		text.setText(getFlowText());
		return commonLyt;
	}
	
	public Class<?> getActiveClassToLaunch() {
		return activeClassToLaunch;
	}
	
	public Stage prev(){
		activeSubSTG--;
		if (activeSubSTG==-1)
			activeSubSTG = subSTGs.length-1;
		return this;
	}	
	
	public Stage next(){
		activeSubSTG++;
		if (activeSubSTG==subSTGs.length)
			activeSubSTG = 0;
		return this;
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

	public String getFlowText(){
		return activeClassToLaunch.getName()+"\n"+subSTGs[activeSubSTG];
	}

	public String getStageName() {
		String className = this.getClass().toString();
		return className.substring(0, className.lastIndexOf("."));
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLogo() {
		return logoId;
	}


	public int getStageID() {
		return stageID;
	}

	public void setStageID(int stageID) {
		this.stageID = stageID;
	}

	public Object getStageState() {

		return new StageState(logoId, stageID, subSTGs, activeSubSTG,stagePreferencesName);

	}

}
