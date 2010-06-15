package com.mru.mrnicoquitter.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.UIUtils;

import static com.mru.mrnicoquitter.Global.*;

public class CigarDBAdapter {

	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;			// Database open/upgrade helper	
	
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
	}

	public void bulkInsert(List<Cigar> list){
		
		SQLiteDatabase db 			= dbHelper.getWritableDatabase();
		try{

			ContentValues 	newValues 	= new ContentValues();
			for (Cigar cigar:list){
				newValues.remove(CIGARS_KEY_DATE);
				newValues.remove(CIGARS_KEY_TYPE);
				newValues.put(CIGARS_KEY_DATE, cigar.getDateStr());
				newValues.put(CIGARS_KEY_TYPE, cigar.getId());
				db.insert(DB_CIGARS_TABLE, null, newValues);
			}
		}finally{
			db.close();
		}
		return;	
	}
	
	public void inserEntry(long selectedItemId, Calendar c) {

		SQLiteDatabase db 	= dbHelper.getWritableDatabase();
		try {
			ContentValues newValues = new ContentValues();
			newValues.put(CIGARS_KEY_DATE, SDF.format(c.getTime()));
			newValues.put(CIGARS_KEY_TYPE, (int) selectedItemId);
			db.insert(DB_CIGARS_TABLE, null, newValues);
		}finally{
			db.close();
			UIUtils.showToastShort(FlowManagerSGTon.getAppContext().getString(R.string.T_CIGARRO_GUARDADO));			
		}		
		return;
	}


	public List<Cigar> getAllEntries () {
		List<Cigar> cigarEntries 	= new ArrayList<Cigar>() ;
		SimpleDateFormat sdf 		= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SQLiteDatabase db 			= dbHelper.getWritableDatabase();
    	Cursor c 					= getAllCursor(db);
		if (c.moveToFirst()) {
			do {
				Cigar cigar = new Cigar();
				cigar.setDateStr(c.getString(CIGARS_COL_DATE));
				cigar.setTipo(c.getInt(CIGARS_COL_TYPE));
				try {
					cigar.setDate(sdf.parse(c.getString(CIGARS_COL_DATE)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cigarEntries.add(cigar);
			} while (c.moveToNext());
		}
        c.close();
        dbHelper.close();
        return cigarEntries;
	}
	
	private Cursor getAllCursor(SQLiteDatabase db){
		return db.query(DB_CIGARS_TABLE, new String[] {CIGARS_KEY_ID, CIGARS_KEY_DATE, CIGARS_KEY_TYPE},
				null, null, null, null, null);
		
	}



}
