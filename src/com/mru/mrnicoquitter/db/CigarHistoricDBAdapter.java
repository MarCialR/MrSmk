package com.mru.mrnicoquitter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;

import android.util.Log;


import static com.mru.mrnicoquitter.Global.*;

public class CigarHistoricDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;				// Database open/upgrade helper	
	
	private static CigarHistoricDBAdapter INSTANCE;

	public static CigarHistoricDBAdapter getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new CigarHistoricDBAdapter(c);
			return INSTANCE;
		} else
			return INSTANCE;
	}

	private CigarHistoricDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new NewDataBaseHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	public CigarHistoricDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
	
	public long insertEntry(Integer day, Integer count) {
		ContentValues newValues = new ContentValues();
		newValues.put(CIGARS_H_KEY_DAY, day);
		newValues.put(CIGARS_H_KEY_COUNT, count);
		long returnValue = db.insert(DB_CIGARS_H_TABLE, null, newValues);
		Log.d("CigarHistoricDBAdapter", "Inserted Cigar with day = " + day + " & count = " + count);
		return returnValue;
	}

	public Cursor getAllHistoricEntries () {
		return db.query(DB_CIGARS_H_TABLE, new String[] {CIGARS_H_KEY_ID,CIGARS_H_KEY_DAY, CIGARS_H_KEY_COUNT},
				null, null, null, null, CIGARS_H_KEY_DAY + " DESC");
	}

}
