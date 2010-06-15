package com.mru.mrnicoquitter.db;

import java.util.ArrayList;
import java.util.List;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.beans.Day;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.UIUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;

import android.util.Log;


import static com.mru.mrnicoquitter.Global.*;

public class CigarHistoricDBAdapter {

	private static Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;				// Database open/upgrade helper	
	
	private static CigarHistoricDBAdapter INSTANCE;

	public static CigarHistoricDBAdapter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CigarHistoricDBAdapter(FlowManagerSGTon.getAppContext());
		}
		return INSTANCE;
	}

	private CigarHistoricDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new NewDataBaseHelper(context);
	}
	
	public void insertEntry(Integer day, Integer count) {

		SQLiteDatabase db 			= dbHelper.getWritableDatabase();		
		try {		
			ContentValues newValues	= new ContentValues();
			newValues.put(CIGARS_H_KEY_DAY, day);
			newValues.put(CIGARS_H_KEY_COUNT, count);
			db.insert(DB_CIGARS_H_TABLE, null, newValues);

		}finally{
			db.close();
			Log.d("CigarHistoricDBAdapter", "Inserted Cigar with day = " + day + " & count = " + count);			
		}				
		return;
	}

	public List<Day> getAllHistoricEntries () {
		List<Day> dayEntries 	= new ArrayList<Day>() ;
		SQLiteDatabase db 		= dbHelper.getWritableDatabase();		
    	Cursor c 				= getAllCursor(db);

		if (c.moveToFirst()) {
			do {
				Day day = new Day();
				day.setDayNumber(c.getInt(CIGARS_H_COL_DAY));
				day.setCigarCount(c.getInt(CIGARS_H_COL_COUNT));
				dayEntries.add(day);
			} while (c.moveToNext());
		}		
        c.close();
        dbHelper.close();
		return dayEntries;
	}

	private Cursor getAllCursor(SQLiteDatabase db){
		return db.query(DB_CIGARS_H_TABLE, new String[] {CIGARS_H_KEY_ID,CIGARS_H_KEY_DAY, CIGARS_H_KEY_COUNT},
				null, null, null, null, CIGARS_H_KEY_DAY + " DESC");
	}	
}
