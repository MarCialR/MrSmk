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
		// el canBeNewDay es necesario porque selectedItemId puede ser cero aunque no este el mañanero
		if (canBeNewDay() && selectedItemId == 0){		// CAMBIO DE DIA !!!!!!!!!
			
			//TODO VALIDACION REPETIDA DIA NUEVO 
			int count 	= CigarDBAdapter.getInstance().changeDay();
			Integer day = dayManagerPreferences.getInt(PREF_DAY_TODAY, 0);
			CigarHistoricDBAdapter.getInstance().insertEntry(day, count);
			
			Editor e 		= dayManagerPreferences.edit();
			Calendar iniDay = Calendar.getInstance(); 
			e.putInt(PREF_DAY_TODAY, iniDay.get(Calendar.DAY_OF_YEAR));
			e.commit();			

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

		Editor e 				= dayManagerPreferences.edit();
		Calendar iniDay 		= Calendar.getInstance(); 
		e.putInt(PREF_DAY_FIRST_DAY, iniDay.get(Calendar.DAY_OF_YEAR));
		e.putInt(PREF_DAY_TODAY, iniDay.get(Calendar.DAY_OF_YEAR));
		e.commit();
		Log.d("DayManagerSGTon", "CREATED DAY PREFERENCES");
	}
	
	private void privGetToday(){
		today = new Day();
		today.setCigarCount(CigarDBAdapter.getInstance().pubGetCount());
		today.setDayNumber(dayManagerPreferences.getInt(PREF_DAY_TODAY, 0));
		today.setMaxCigarsToday(10);
		today.setPreviousDaySaved(0.68);		
		
	}

	public boolean canBeNewDay() {
		Calendar today = Calendar.getInstance();
		if (dayManagerPreferences.getInt(PREF_DAY_TODAY, 0)<today.get(Calendar.DAY_OF_YEAR))
			return true;
		return false;
	}
}
