package com.mru.mrnicoquitter.stage;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.PhaseState;

import android.content.Context;
import android.content.res.Resources;

public class P2_Phase extends Phase {

	public P2_Phase(Context _context) {
		id = PHASE_2_CODE;
		myContext = _context;
		logoId = R.drawable.etapa1;
		phasePreferences = myContext.getSharedPreferences(PREFS_PHASE_2, 0);
	}

	public P2_Phase(Context _context, PhaseState _state) {
		initCommons(_context, _state);
	}

	@Override
	public int[] getCodes() {
		return Resources.getSystem().getIntArray(R.array.PHASE_2_Stages_Codes);
		
	}

	@Override
	public String[] getNames() {
		return Resources.getSystem().getStringArray(R.array.PHASE_2_Stages_Descriptions);
	}
}