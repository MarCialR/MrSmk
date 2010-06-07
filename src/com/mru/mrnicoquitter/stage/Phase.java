package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.PhaseState;

public abstract class Phase {
	// ===========================================================
	// Fields
	// ===========================================================	
	protected int id;
	protected int logoId;

	protected SharedPreferences phasePreferences;
	protected String phasePreferencesName;
	protected Context myContext;

	
	// ===========================================================
	// 		Constructors & Initialization
	// ===========================================================	
	
	

	public static Phase initPhase (Context context, PhaseState phaseState){
		
		switch (phaseState.getId()){
		
			case PHASE_1_CODE:
				return new P1_Phase(context,phaseState);
				
			case PHASE_2_CODE:
				return new P2_Phase(context,phaseState);
	
			case PHASE_3_CODE:
				return new P1_Phase(context,phaseState);
	
			case PHASE_4_CODE:
				return new P1_Phase(context,phaseState);
				
			default:
				return new P1_Phase(context,phaseState);				
		}
	}

	protected void initCommons(Context _context, PhaseState _phaseState) {
		myContext				= _context;
		logoId					= _phaseState.getLogoId();
		id						= _phaseState.getId();
		phasePreferencesName	= _phaseState.getPhasePreferencesName();
		phasePreferences 		= myContext.getSharedPreferences(phasePreferencesName, Context.MODE_PRIVATE);		
	}
	
	public View getCommonLayout(LayoutInflater inflater, int contentLayout){

		LinearLayout commonLyt = (LinearLayout)inflater.inflate(R.layout.lay_common, null);
		inflater.inflate(contentLayout, commonLyt,true);
		
		ImageView logo	= (ImageView) commonLyt.findViewById(R.id.Logo);
		TextView text	= (TextView) commonLyt.findViewById(R.id.StageInfo);
		logo.setBackgroundResource(logoId);
		text.setText("getFlowText()");
		return commonLyt;
	}
	


	public String getStageName() {
		String className = this.getClass().toString();
		return className.substring( className.lastIndexOf(".")+1, className.length());
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getLogo() {
		return logoId;
	}


	public int getStageID() {
		return id;
	}

	public void setStageID(int stageID) {
		this.id = stageID;
	}

	public PhaseState getPhaseState(int activeStageCode) {
		return new PhaseState(logoId, id, phasePreferencesName, activeStageCode);
	}

	abstract public int[] getCodes();
	abstract public String[] getNames();
}
