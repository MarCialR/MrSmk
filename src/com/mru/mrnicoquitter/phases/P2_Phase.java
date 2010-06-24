package com.mru.mrnicoquitter.phases;

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.PhaseState;

import android.content.Context;

public class P2_Phase extends Phase {

	public P2_Phase(Context _context) {
		super();		
		phaseId 						= PHASE_2_CODE;
		myContext 				= _context;
		logoId 					= R.drawable.etapa1;
		phasePreferencesName 	= PREFS_PHASE_2;
		phasePreferences 		= myContext.getSharedPreferences(phasePreferencesName, Context.MODE_PRIVATE);		
		
		
	}

	public P2_Phase(Context _context, PhaseState _state) {
		super();		
		initCommons(_context, _state);
	}

	@Override
	public int[] getCodes() {
		return twoCodes;
		
	}

	@Override
	public String[] getNames() {
		return twoDescriptions;
	}
}
