package com.mru.mrnicoquitter.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.mru.mrnicoquitter.beans.Cigar;
import static com.mru.mrnicoquitter.Global.*;

public class CigarDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private myDbHelper dbHelper;				// Database open/upgrade helper	
	
	private static CigarDBAdapter INSTANCE;

	public static CigarDBAdapter getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new CigarDBAdapter(c);
			return INSTANCE;
		} else
			return INSTANCE;
	}

	private CigarDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new myDbHelper(context, DATABASE_NAME, null,DB_CIGARS_VERSION);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	public CigarDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
	public void bulkInsert(List<Cigar> list){

		try{
			ContentValues 	newValues = new ContentValues();
			for (Cigar cigar:list){
				newValues.remove(CIGARS_KEY_DATE);
				newValues.remove(CIGARS_KEY_TYPE);
				newValues.put(CIGARS_KEY_DATE, cigar.getDateStr());
				newValues.put(CIGARS_KEY_TYPE, cigar.getId());
				db.insert(DB_CIGARS_TABLE, null, newValues);
			}
		}catch (Exception e) {
			close();
		}
		return;	
	}
	
	public long insertEntry(Cigar _myObject) {
		// Create a new row of values to insert.
		ContentValues newValues = new ContentValues();
		//newValues.
		// Assign values for each row.
		newValues.put(CIGARS_KEY_DATE, _myObject.getDateStr());
		newValues.put(CIGARS_KEY_TYPE, _myObject.getId());
		// Insert the row into your table
		return db.insert(DB_CIGARS_TABLE, null, newValues);		
	}

	public boolean removeEntry(long _rowIndex) {
		return db.delete(DB_CIGARS_TABLE, CIGARS_KEY_ID +
				"=" + _rowIndex, null) > 0;
	}
	public Cursor getAllEntries () {
		return db.query(DB_CIGARS_TABLE, new String[] {CIGARS_KEY_ID, CIGARS_KEY_DATE, CIGARS_KEY_TYPE},
				null, null, null, null, null);
		//null, null, null, null, "date DESC");
	}
	
	public String getAllEntriesToSend(){
    	Cursor c = getAllEntries();
    	StringBuffer sb = new StringBuffer(); 
        if (c.moveToFirst()){
        	do {
        		Cigar cigar = new Cigar();
        		cigar.setDateStr(c.getString(CIGARS_COL_DATE));
        		cigar.setTipo(c.getInt(CIGARS_COL_TYPE));
        		
        		sb.append(cigar.toSave()).append(NEWLINE);
        	}while (c.moveToNext());
        }
        return sb.toString();
	}
	public String getAllEntriesToSendAsJSON(){
		Gson gson = new Gson();
		List<Cigar> cigars = new ArrayList<Cigar>();
    	Cursor c = getAllEntries();
        if (c.moveToFirst()){
        	do {
        		Cigar cigar = new Cigar();
        		cigar.setDateStr(c.getString(CIGARS_COL_DATE));
        		cigar.setTipo(c.getInt(CIGARS_COL_TYPE));
        		
        		cigars.add(cigar);
        	}while (c.moveToNext());
        }
        return gson.toJson(cigars);
	}
	
	/*
BagOfPrimitives obj = new BagOfPrimitives();
Gson gson = new Gson();
String json = gson.toJson(obj);  
	 */
	
	public Cigar getEntry(long _rowIndex) {
		Cigar objectInstance = new Cigar();
		//TODO Return a cursor to a row from the database and
		//use the values to populate an instance of MyObject
		return objectInstance;
	}
	public int updateEntry(long _rowIndex, Cigar _myObject) {
		String where 				= CIGARS_KEY_ID + "=" + _rowIndex;
		ContentValues contentValues = new ContentValues();
		//TODO fill in the ContentValue based on the new object
		return db.update(DB_CIGARS_TABLE, contentValues, where, null);
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
			_db.execSQL(DB_CIGARS_CREATE);
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
			_db.execSQL(DB_CIGARS_DROP);
			// Create a new one.
			onCreate(_db);
		}
	}
}
