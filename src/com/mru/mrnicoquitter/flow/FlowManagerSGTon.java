package com.mru.mrnicoquitter.flow;

import static com.mru.mrnicoquitter.Global.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.mru.mrnicoquitter.beans.IntentExtra;
import com.mru.mrnicoquitter.beans.PhaseState;
import com.mru.mrnicoquitter.beans.Stage;
import com.mru.mrnicoquitter.db.FlowObjectDBAdapter;
import com.mru.mrnicoquitter.phases.P1_Phase;
import com.mru.mrnicoquitter.phases.P2_Phase;
import com.mru.mrnicoquitter.phases.Phase;

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
	private static ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	
	private static int activeStageIndex;	
	private static int[] phaseStagesCodes;
	private static String[] phaseStagesNames ;	
	private static Stage actualStage;


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
		globalPreferences 		= myContext.getSharedPreferences(PREFS_GLOBAL, Context.MODE_WORLD_READABLE);
		String dehidratedPhase 	= globalPreferences.getString(PREF_ACTUAL_PHASE_DEHIDRATED, null);
	
		if (null != dehidratedPhase)
			hidrataPhase(dehidratedPhase);
		else
			setPhase(PHASE_1_CODE);
		
	}

	// ===========================================================
	// 		GETTERs & SETTERs
	// ===========================================================	
	
	public static int getActiveStageCode(){
		return phaseStagesCodes[activeStageIndex];
	}
	
	public static FlowManagerSGTon getInstance() {
		return INSTANCE;
	}
	public static Context getAppContext() {
		return myContext;
	}
	
	static public SharedPreferences getGlobalPreferences() {
		if (globalPreferences == null)
			globalPreferences = myContext.getSharedPreferences(PREFS_GLOBAL, Context.MODE_WORLD_READABLE);
		return globalPreferences;
	}	

	public static Phase getPhase() {
		return phase;
	}

	private static void hidrataPhase(String dehidratedPhase){
		
		PhaseState stageState = ((PhaseState)gson.fromJson(dehidratedPhase, PhaseState.class));
		phase = Phase.initPhase(myContext, stageState);
		phaseStagesCodes = phase.getCodes();
		phaseStagesNames = phase.getNames();
	}

	public static Phase setPhase(int phaseID) {
		clearPreferences();
		switch (phaseID) {
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
		phaseStagesCodes = phase.getCodes();
		phaseStagesNames = phase.getNames();
		activeStageIndex = 0;
		Log.d("FlowManagerSGTon", "Cambiada FASE a: " + phase.getPhaseName());

		String deHidratedPhaseState = null;
		try {
			deHidratedPhaseState = mapper.writeValueAsString(phase
					.getPhaseState(phaseStagesCodes[activeStageIndex]));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		globalPreferences.edit().putString(PREF_ACTUAL_PHASE_DEHIDRATED,
				deHidratedPhaseState).putInt(PREF_ACTUAL_PHASE_CODE, phaseID)
				.commit();
		return phase;

	}

private static void setStage(){
	FlowObjectDBAdapter flowDB = FlowObjectDBAdapter.getInstance(myContext).open();
	String deH = flowDB.getEntry(getActiveStageCode());
	flowDB.close();		
	try {
		actualStage = mapper.readValue(deH, Stage.class);
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	public static Stage getActualStage() {
	return actualStage;
}

	// ===========================================================
	// 		UTILITIES
	// ===========================================================
	private static void clearPreferences() {
		// TODO Auto-generated method stub
		
	}
	public static void forceNext(){
		if (actualStage!= null)
			next();
		setStage();
		
	}

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
		sb.append(phase.getPhaseName()).append(NEWLINE);
		sb.append("SUB_STAGES").append(NEWLINE);
		int counter = 0;
		for(int stageIt:phaseStagesCodes){
			if (counter == activeStageIndex)
				sb.append("* - ");
			sb.append("(").append(phaseStagesCodes[counter]).append(")").append(phaseStagesNames[counter]).append(NEWLINE);;
			counter++;
		}
		return sb.toString();
	}
	public static CharSequence getHeaderText() {
		// TODO Auto-generated method stub
		return phase.getPhaseName();
	}


	public static Intent getIntent(Activity splash) {
		setStage();
		/* Create an Intent that will start the Menu-Activity. */
		//Class<?> goTo = phase.getActiveClassToLaunch();
		
		 Class<?> goTo = null;
		try {
			goTo = Class.forName(actualStage.getActivity());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent i = new Intent(splash, goTo);
		for (IntentExtra ie: actualStage.getExtras())
			i.putExtra(ie.getName(), ie.getContents());
		
		return i; 
	}


	

}
