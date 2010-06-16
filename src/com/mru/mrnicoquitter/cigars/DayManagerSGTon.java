package com.mru.mrnicoquitter.cigars;

import static com.mru.mrnicoquitter.Global.*;

import java.util.Calendar;

import com.mru.mrnicoquitter.beans.Day;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.db.CigarHistoricDBAdapter;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class DayManagerSGTon {

	// ===========================================================
	// Fields
	// ===========================================================	
	
	private static DayManagerSGTon INSTANCE;
	private static Context myContext;
	private static SharedPreferences dayManagerPreferences;	
	private static Day today;

	
	private DayManagerSGTon() {
		myContext				= FlowManagerSGTon.getAppContext();
		dayManagerPreferences 	= myContext.getSharedPreferences(PREFS_DAY_MANAGER, Context.MODE_WORLD_READABLE);
		privGetToday();
	}

	// ===========================================================
	// 		GETTERs & SETTERs
	// ===========================================================	
	
	public static DayManagerSGTon getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DayManagerSGTon();
		return INSTANCE;
	}


	// ===========================================================
	// 		UTILITIES
	// ===========================================================
	private static void clearPreferences() {
		// TODO Auto-generated method stub
		
	}
	public void insert(long selectedItemId, Calendar _c){
		
		if (_c == null)
			_c = Calendar.getInstance();
		
		if (selectedItemId == 0){		// CAMBIO DE DIA !!!!!!!!!
			int count = CigarDBAdapter.getInstance().changeDay();
			Integer day = dayManagerPreferences.getInt("DAY", 0);
			CigarHistoricDBAdapter.getInstance().insertEntry(day, count);
			dayManagerPreferences.edit().putInt("DAY", day.intValue()-1).commit();
		}
		
		CigarDBAdapter.getInstance().inserEntry(selectedItemId, _c);
		privGetToday();
	}
	
	public Day getToday(){
		return today;
	}

	public static void createPreferences() {
		myContext				= FlowManagerSGTon.getAppContext();
		dayManagerPreferences 	= myContext.getSharedPreferences(PREFS_DAY_MANAGER, Context.MODE_WORLD_READABLE);

		Editor e = dayManagerPreferences.edit();
		e.putLong("FIRST_DAY", Calendar.getInstance().getTimeInMillis());
		e.putInt("DAY", 0);
		e.commit();
		Log.d("DayManagerSGTon", "CREATED DAY PREFERENCES");
	}
	
	private void privGetToday(){
		today = new Day();
		today.setCigarCount(CigarDBAdapter.getInstance().pubGetCount());
		today.setDayNumber(dayManagerPreferences.getInt("DAY", 0));
		today.setMaxCigarsToday(10);
		today.setPreviousDaySaved(0.68);		
		
	}
}
