package com.mru.mrnicoquitter.db;

import com.mru.mrnicoquitter.basura.Cigar;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
public class MyDBAdapter {

	private static final String DATABASE_NAME 	= "mrQuitter.db";
	private static final String DATABASE_TABLE 	= "cigars";
	private static final int DATABASE_VERSION 	= 1;
	public static final String KEY_ID			= "_id";	// The index (key) column name for use in where clauses.
	public static final String KEY_DATE			= "date";	// The name and column index of each column in your database.
	public static final int COLUMN_DATE 		= 1;
	public static final String KEY_TYPE			= "type";	// The name and column index of each column in your database.
	public static final int COLUMN_TYPE 		= 2;
	private static final String DATABASE_CREATE = "create table " +	DATABASE_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_DATE + " date not null, "+ KEY_TYPE + " integer not null);";
	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private myDbHelper dbHelper;				// Database open/upgrade helper

	public MyDBAdapter(Context _context) {
		context = _context;
		dbHelper = new myDbHelper(context, DATABASE_NAME, null,DATABASE_VERSION);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	public MyDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
	
	public long insertEntry(Cigar _myObject) {
		// Create a new row of values to insert.
		ContentValues newValues = new ContentValues();
		//newValues.
		// Assign values for each row.
		newValues.put(KEY_DATE, _myObject.getDate());
		newValues.put(KEY_TYPE, _myObject.getId());
		// Insert the row into your table
		return db.insert(DATABASE_TABLE, null, newValues);		
	}
	private Context getResources() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean removeEntry(long _rowIndex) {
		return db.delete(DATABASE_TABLE, KEY_ID +
				"=" + _rowIndex, null) > 0;
	}
	public Cursor getAllEntries () {
		return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_DATE, KEY_TYPE},
				null, null, null, null, null);
	}
	public Cigar getEntry(long _rowIndex) {
		Cigar objectInstance = new Cigar();
		//TODO Return a cursor to a row from the database and
		//use the values to populate an instance of MyObject
		return objectInstance;
	}
	public int updateEntry(long _rowIndex, Cigar _myObject) {
		String where = KEY_ID + "=" + _rowIndex;
		ContentValues contentValues = new ContentValues();
		//TODO fill in the ContentValue based on the new object
		return db.update(DATABASE_TABLE, contentValues, where, null);
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
			_db.execSQL(DATABASE_CREATE);
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
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			// Create a new one.
			onCreate(_db);
		}
	}
}