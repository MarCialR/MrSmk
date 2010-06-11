package com.mru.mrnicoquitter.db.flow;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.mru.mrnicoquitter.beans.Stage;
import com.mru.mrnicoquitter.db.NewDataBaseHelper;

import static com.mru.mrnicoquitter.Global.*;

public class FlowObjectDBAdapter {

	private SQLiteDatabase db;		// Variable to hold the database instance
	private final Context context;	// Context of the application using the database.
	private NewDataBaseHelper dbHelper;	// Database open/upgrade helper	
	
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
		dbHelper 	= new NewDataBaseHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}


	public void bulkInsert(List<String> list){
		Log.d("CigarHistoricDBAdapter","EXECUTING FLOW DB BULK INSERTS:");
		for (String flowItemInsert : list){
			Log.d("CigarHistoricDBAdapter",flowItemInsert);
			db.execSQL(flowItemInsert);
		}
		return;	
	}

	public long insertEntry(Stage _myObject) {
		ContentValues newValues = new ContentValues();
		newValues.put(FLOW_KEY_ID, _myObject.getObject_id());
		newValues.put(FLOW_KEY_OBJECT, _myObject.getObject_id());
		return db.insert(DB_FLOW_TABLE, null, newValues);		
	}



	public String getEntry(int _rowIndex) {
		String objectInstance = null;
//        Cursor c = db.query(DB_FLOW_TABLE, new String[] {FLOW_KEY_OBJECT},
//        		FLOW_KEY_ID + " = " + 1004, null, null, null, null);
//        Cursor c = db.query("SELECT " + FLOW_KEY_OBJECT +
//        		 
//                " FROM " + DB_FLOW_TABLE
//
//                + " WHERE Age > 10 LIMIT 7;",
//
//                null);
        String sqlQuery = "SELECT " + FLOW_KEY_OBJECT +
        " FROM " + DB_FLOW_TABLE
        + " WHERE " + FLOW_KEY_ID + " = " + _rowIndex;
		Log.w("FlowObjectDBAdapter",sqlQuery);
		
        Cursor c = db.rawQueryWithFactory(null, sqlQuery, null, null);
		if (c.moveToFirst()) {
			objectInstance = c.getString(0);
		}
		c.close();
		return objectInstance;
	}
	
	public void cleanDB() {
		db.execSQL("DELETE FROM " + DB_FLOW_TABLE);
		System.out.println("FLOW DB: DELETING ALL ROWS...");
		
	}	
	public FlowObjectDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}

//	public boolean removeEntry(long _rowIndex) {
//	return db.delete(DB_FLOW_TABLE, FLOW_KECOL_ID +
//			"=" + _rowIndex, null) > 0;
//}


	
//	public Cursor getAllEntries () {
//	return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_DATE, KEY_OBJECT},
//			null, null, null, null, null);
//	//null, null, null, null, "date DESC");
//}	
//	public int updateEntry(long _rowIndex, FlowItem _myObject) {
//	String where 				= FLOW_COL_ID + "=" + _rowIndex;
//	ContentValues contentValues = new ContentValues();
//	//TODO fill in the ContentValue based on the new object
//	return db.update(DB_FLOW_TABLE, contentValues, where, null);
//}	
}
