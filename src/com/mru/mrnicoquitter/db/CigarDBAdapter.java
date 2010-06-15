package com.mru.mrnicoquitter.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;

import com.google.gson.Gson;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.UIUtils;

import static com.mru.mrnicoquitter.Global.*;

public class CigarDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;				// Database open/upgrade helper	
	
	private static CigarDBAdapter INSTANCE;

//	public static CigarDBAdapter getInstance(Context c) {
//		if (INSTANCE == null) {
//			INSTANCE = new CigarDBAdapter(c);
//			return INSTANCE;
//		} else
//			return INSTANCE;
//	}

	public static CigarDBAdapter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CigarDBAdapter(FlowManagerSGTon.getAppContext());
		}
			return INSTANCE;
	}
	
	private CigarDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new NewDataBaseHelper(context);
		try {
			db = dbHelper.getWritableDatabase();
		}
		catch (SQLiteException ex){
			db = dbHelper.getReadableDatabase();	
		}
	}

	private void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return;
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
		open();
		// Create a new row of values to insert.
		ContentValues newValues = new ContentValues();
		//newValues.
		// Assign values for each row.
		newValues.put(CIGARS_KEY_DATE, _myObject.getDateStr());
		newValues.put(CIGARS_KEY_TYPE, _myObject.getId());
		// Insert the row into your table
		long que = 0L;
		try {
			que = db.insert(DB_CIGARS_TABLE, null, newValues);
		}
		catch(Exception e){
			System.out.println(e);
			close();    
		} finally{
			close();
		}
		
		return 		que;
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
        c.close();
        close();
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
        c.close();
        close();
        return gson.toJson(cigars);
	}

	public static void inserttt(Cigar cigar) {
		CigarDBAdapter dba = CigarDBAdapter.getInstance();
		dba.insertEntry(cigar);
		UIUtils.showToastShort(FlowManagerSGTon.getAppContext().getString(R.string.T_CIGARRO_GUARDADO));
	}
}
