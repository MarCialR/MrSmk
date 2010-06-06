package com.mru.mrnicoquitter.flow;

import static com.mru.mrnicoquitter.Global.*;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mru.mrnicoquitter.beans.PhaseState;
import com.mru.mrnicoquitter.stage.P2_Phase;
import com.mru.mrnicoquitter.stage.Phase;
import com.mru.mrnicoquitter.stage.P1_Phase;
import com.mru.mrnicoquitter.ui.AppUtils;

public class FlowManagerSGTon {

	// ===========================================================
	// Fields
	// ===========================================================	
	
	private static FlowManagerSGTon INSTANCE;
	private static Context myContext;
	private static Phase phase;
	//private static String[] 
	private static SharedPreferences globalPreferences;	
	private static Gson gson;


	// ===========================================================
	// 		Constructors & Initialization
	// ===========================================================	
	
	public static Phase initManager(Context applicationContext) {
		myContext 	= applicationContext;
		INSTANCE 	= new FlowManagerSGTon();
		return phase;
	}
	
	private FlowManagerSGTon() {

		gson 					= new Gson();	
		globalPreferences 		= myContext.getSharedPreferences(PREFS_GLOBAL, Context.MODE_PRIVATE);
		String dehidratedStage 	= globalPreferences.getString(PREF_ACTUAL_PHASE_DEHIDRATED, null);
	
		if (null != dehidratedStage)
			phase = hidrataStage(dehidratedStage);
		else
			setPhase(PHASE_1_CODE);
		
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

	public static Phase getPhase() {
		return phase;
	}

	public static Phase hidrataStage(String dehidratedStage){
		
		PhaseState stageState = ((PhaseState)gson.fromJson(dehidratedStage, PhaseState.class));
		return Phase.initPhase(myContext, stageState);
	}

	public static void setStage(PhaseState stageState){
		
	}
	
	public static void setPhase(int phaseID){
		clearPreferences();
		switch (phaseID){
			case PHASE_1_CODE:
				phase = new P1_Phase(myContext);
				break;
			case PHASE_2_CODE:
				phase = new P2_Phase(myContext);
				break;
			case PHASE_3_CODE:
				phase = new P2_Phase(myContext);
				break;
			case PHASE_4_CODE:
				phase = new P2_Phase(myContext);
				break;				
			default:
				throw new RuntimeException();
		}
		
		Log.d("FlowManagerSGTon", "Cambiada FASE a: "+ phase.getStageName());	
		String deHidratedStageState = gson.toJson(phase.getStageState());
		globalPreferences.edit().putString (PREF_ACTUAL_PHASE_DEHIDRATED,deHidratedStageState).putInt(PREF_ACTUAL_PHASE_CODE, phaseID).commit();
		phaseStagesCodes = phase.getCodes();
		phaseStagesNames = phase.getNames();
		activeStageIndex = 0;
		
	}


	private static void clearPreferences() {
		// TODO Auto-generated method stub
		
	}


	// ===========================================================
	// 		UTILITIES
	// ===========================================================
	
	private static int[] phaseStagesCodes;
	private static String[] phaseStagesNames ;
	private static int activeStageIndex;	

	public static void  prev(){
		activeStageIndex--;
		if (activeStageIndex==-1)
			activeStageIndex = phaseStagesCodes.length-1;
	}	
	
	public static void next(){
		activeStageIndex++;
		if (activeStageIndex==phaseStagesCodes.length)
			activeStageIndex = 0;
	}
	
	public static String getInfo(){
		StringBuffer sb = new StringBuffer();
		sb.append(phase.getStageName()).append(NEWLINE);
		sb.append("SUB_STAGES").append(NEWLINE);
		int counter = 0;
		for(int stageIt:phaseStagesCodes){
			if (counter == activeStageIndex)
				sb.append("* - ");
			sb.append(phaseStagesNames[activeStageIndex]).append(NEWLINE);;
			counter++;
		}
		return sb.toString();
	}

	public static void setPhaseStagesCodes(int[] phaseStagesCodes) {
		FlowManagerSGTon.phaseStagesCodes = phaseStagesCodes;
	}

	public static void setPhaseStagesNames(String[] phaseStagesNames) {
		FlowManagerSGTon.phaseStagesNames = phaseStagesNames;
	}
	

}
