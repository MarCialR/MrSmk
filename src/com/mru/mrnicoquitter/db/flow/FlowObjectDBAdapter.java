package com.mru.mrnicoquitter.db.flow;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.google.gson.Gson;

import com.mru.mrnicoquitter.beans.FlowItem;
import static com.mru.mrnicoquitter.Global.*;

public class FlowObjectDBAdapter {

	private SQLiteDatabase db;		// Variable to hold the database instance
	private final Context context;	// Context of the application using the database.
	private myDbHelper dbHelper;	// Database open/upgrade helper	
	
	private static FlowObjectDBAdapter INSTANCE;

	public static FlowObjectDBAdapter getInstance(Context c) {
		if (INSTANCE == null) {
			INSTANCE = new FlowObjectDBAdapter(c);
			return INSTANCE;
		} else
			return INSTANCE;
	}

	private FlowObjectDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new myDbHelper(context, DATABASE_NAME, null,DB_FLOW_VERSION);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	public FlowObjectDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
//	public void bulkInsert(List<FlowItem> list){
//
//		try{
//			ContentValues 	newValues = new ContentValues();
//			for (FlowItem cigar:list){
//				newValues.remove(KEY_DATE);
//				newValues.remove(KEY_OBJECT);
//				newValues.put(KEY_DATE, cigar.getDateStr());
//				newValues.put(KEY_OBJECT, cigar.getId());
//				db.insert(DATABASE_TABLE, null, newValues);
//			}
//		}catch (Exception e) {
//			close();
//		}
//		return;	
//	}
	
	public long insertEntry(FlowItem _myObject) {
		ContentValues newValues = new ContentValues();
		newValues.put(FLOW_COL_ID, _myObject.getId());
		newValues.put(FLOW_KEY_OBJECT, _myObject.getId());
		return db.insert(DB_FLOW_TABLE, null, newValues);		
	}

	public boolean removeEntry(long _rowIndex) {
		return db.delete(DB_FLOW_TABLE, FLOW_COL_ID +
				"=" + _rowIndex, null) > 0;
	}
//	public Cursor getAllEntries () {
//		return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_DATE, KEY_OBJECT},
//				null, null, null, null, null);
//		//null, null, null, null, "date DESC");
//	}

	public FlowItem getEntry(int _rowIndex) {
		FlowItem objectInstance = null;
        Cursor c = db.query(DB_FLOW_TABLE, new String[] {FLOW_COL_ID},
				null, null, null, null, null);
		if (c.moveToFirst()) {
			objectInstance = new FlowItem();
			objectInstance.setId(_rowIndex);
			objectInstance.setJson(c.getString(FLOW_COL_OBJECT));
		}
		c.close();
		return objectInstance;
	}
	public int updateEntry(long _rowIndex, FlowItem _myObject) {
		String where 				= FLOW_COL_ID + "=" + _rowIndex;
		ContentValues contentValues = new ContentValues();
		//TODO fill in the ContentValue based on the new object
		return db.update(DB_FLOW_TABLE, contentValues, where, null);
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
			_db.execSQL(DB_FLOW_CREATE);
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
			_db.execSQL(DB_FLOW_DROP);
			// Create a new one.
			onCreate(_db);
		}
	}
}
