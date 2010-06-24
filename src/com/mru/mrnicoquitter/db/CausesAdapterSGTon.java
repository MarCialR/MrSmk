package com.mru.mrnicoquitter.db;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.cigars.DayManagerSGTon;

import android.content.Context;

public class CausesAdapterSGTon {

	private static CausesAdapterSGTon INSTANCE;
	private static CausesAdapter list;

	private CausesAdapterSGTon() {
	}

	public static CausesAdapterSGTon getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new CausesAdapterSGTon();
			list = new CausesAdapter(c);

		}
		return INSTANCE;
	}

	public CausesAdapter getList() {
		return list;
	}
	public void refreshList(){
		
	}

}
