package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.PhaseState;

import android.content.Context;
import android.content.res.Resources;

public class P1_Phase extends Phase {
	
	public P1_Phase(Context _context){
		id						= PHASE_1_CODE;
		myContext				= _context;
		logoId 					= R.drawable.etapa0;
		phasePreferencesName 	= PREFS_PHASE_1;
		phasePreferences 		= myContext.getSharedPreferences(phasePreferencesName, Context.MODE_PRIVATE);		phasePreferences 	= myContext.getSharedPreferences(phasePreferencesName, Context.MODE_PRIVATE);
	}

	public P1_Phase(Context _context, PhaseState _state) {
		initCommons(_context, _state);
	}

	@Override
	public int[] getCodes() {
		return oneCodes;//Resources.getSystem().getIntArray(R.array.phaseOneStagesCodes);
		
	}

	@Override
	public String[] getNames() {
		return oneDescriptions;//Resources.getSystem().getStringArray(R.array.phaseOneStagesDescriptions);
	}
}
