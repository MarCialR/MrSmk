package com.mru.mrnicoquitter.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


import static com.mru.mrnicoquitter.Global.*;

public class CigarHistoricDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private myDbHelper dbHelper;				// Database open/upgrade helper	
	
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
		dbHelper 	= new myDbHelper(context, DATABASE_NAME, null, DB_CIGARS_H_VERSION);
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
	public void bulkInsert(List<String> list){
		Log.d("CigarHistoricDBAdapter","EXECUTING CIGARS HISTORIC DB BULK INSERTS:");
		for (String flowItemInsert : list){
			Log.d("CigarHistoricDBAdapter",flowItemInsert);
			db.execSQL(flowItemInsert);
		}
		return;	
	}
	
	public long insertEntry(Integer day, Integer count) {
		// Create a new row of values to insert.
		ContentValues newValues = new ContentValues();
		//newValues.
		// Assign values for each row.
		newValues.put(CIGARS_H_KEY_DAY, day);
		newValues.put(CIGARS_H_KEY_COUNT, count);
		// Insert the row into your table
		return db.insert(DB_CIGARS_H_TABLE, null, newValues);		
	}

	public Cursor getAllEntries () {
		return db.query(DB_CIGARS_H_TABLE, new String[] {CIGARS_H_KEY_DAY, CIGARS_H_KEY_COUNT},
				null, null, null, null, CIGARS_H_KEY_DAY + " DESC");
	}
	
//	public String getAllEntriesToSend(){
//    	Cursor c = getAllEntries();
//    	StringBuffer sb = new StringBuffer(); 
//        if (c.moveToFirst()){
//        	do {
//        		Cigar cigar = new Cigar();
//        		cigar.setDateStr(c.getString(CIGARS_H_COL_DATE));
//        		cigar.setTipo(c.getInt(CIGARS_H_COL_TYPE));
//        		
//        		sb.append(cigar.toSave()).append(NEWLINE);
//        	}while (c.moveToNext());
//        }
//        return sb.toString();
//	}
//	public String getAllEntriesToSendAsJSON(){
//		Gson gson = new Gson();
//		List<Cigar> cigars = new ArrayList<Cigar>();
//    	Cursor c = getAllEntries();
//        if (c.moveToFirst()){
//        	do {
//        		Cigar cigar = new Cigar();
//        		cigar.setDateStr(c.getString(CIGARS_H_COL_DATE));
//        		cigar.setTipo(c.getInt(CIGARS_H_COL_TYPE));
//        		
//        		cigars.add(cigar);
//        	}while (c.moveToNext());
//        }
//        return gson.toJson(cigars);
//	}

	public void cleanDB() {
		db.getSyncedTables();
		db.execSQL("DELETE FROM " + DB_CIGARS_H_TABLE);
		Log.d("CigarHistoricDBAdapter","CIGARS HISTORIC DB: DELETING ALL ROWS...");
		
	}	
	
	private static class myDbHelper extends SQLiteOpenHelper {
		public myDbHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		// Called when no database exists in
		// disk and the helper class needs
		// to create a new one.
		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DB_CIGARS_H_DROP);
			_db.execSQL(DB_CIGARS_H_CREATE);
		}
		// Called when there is a database version mismatch meaning that
		// the version of the database on disk needs to be upgraded to
		// the current version.
		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
				int _newVersion) {
			// Log the version upgrade.
			Log.w("TaskDBAdapter", "Upgrading from version " +
					_oldVersion + " to " +
					_newVersion +
			", which will destroy all old data");
			// Upgrade the existing database to conform to the new version.
			// Multiple previous versions can be handled by comparing
			// _oldVersion and _newVersion values.
			// The simplest case is to drop the old table and create a
			// new one.
			_db.execSQL(DB_CIGARS_H_DROP);
			// Create a new one.
			onCreate(_db);
		}
	}
}
