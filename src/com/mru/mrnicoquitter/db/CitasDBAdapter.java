package com.mru.mrnicoquitter.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;


import static com.mru.mrnicoquitter.Global.*;

public class CitasDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;				// Database open/upgrade helper	
	
	private static CitasDBAdapter INSTANCE;

	
	public String getEntry(int _rowIndex) {
		String objectInstance = null;
        String sqlQuery = "SELECT " + CITAS_KEY_TEXT_EN +
        " FROM " + DB_CITAS_TABLE
        + " WHERE " + CITAS_KEY_ID + " = " + _rowIndex;
		Log.w("FlowObjectDBAdapter",sqlQuery);
		
        Cursor c = db.rawQueryWithFactory(null, sqlQuery, null, null);
		if (c.moveToFirst()) {
			objectInstance = c.getString(0);
		}
		c.close();
		return objectInstance;
	}	
	
	public static CitasDBAdapter getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new CitasDBAdapter(c);
			return INSTANCE;
		} else
			return INSTANCE;
	}

	private CitasDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new NewDataBaseHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	public CitasDBAdapter open() throws SQLException {
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

	public Cursor getAllHistoricEntries () {
		return db.query(DB_CIGARS_H_TABLE, new String[] {CIGARS_H_KEY_ID,CIGARS_H_KEY_DAY, CIGARS_H_KEY_COUNT},
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
		db.execSQL("DELETE FROM " + DB_CIGARS_H_TABLE);
		Log.d("CigarHistoricDBAdapter","CIGARS HISTORIC DB: DELETING ALL ROWS...");
		
	}	
	

}
