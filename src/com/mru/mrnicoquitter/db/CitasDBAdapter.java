package com.mru.mrnicoquitter.db;

import com.mru.mrnicoquitter.beans.Cita;
import com.mru.mrnicoquitter.utils.Utils;

import static com.mru.mrnicoquitter.Global.*;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class CitasDBAdapter {

	private SQLiteDatabase db;					// Variable to hold the database instance
	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;			// Database open/upgrade helper	
	
	private static CitasDBAdapter INSTANCE;

	
	private Cita getCitaById(int _rowIndex) {

		Cita cita 		= new Cita();
        String sqlQuery = 	  "SELECT " + CITAS_KEY_TEXT_EN + ", " + CITAS_KEY_AUT_EN 
        					+ " FROM " + DB_CITAS_TABLE
        					+ " WHERE " + CITAS_KEY_ID + " = " + _rowIndex;
		
        Log.d("FlowObjectDBAdapter",sqlQuery);
		
        Cursor c = db.rawQueryWithFactory(null, sqlQuery, null, null);
		if (c.moveToFirst()) {
			
			cita.setText(c.getString(0));
			cita.setAuthor(c.getString(1));
		}
		c.close();
        Log.d("FlowObjectDBAdapter","Found Cita = " + cita.getText());
		setUsed(_rowIndex);
		return cita;
	}	
	public Cita getRandomEntry() {

        String sqlQuery = 	  "SELECT " + CITAS_KEY_ID
        					+ " FROM " + DB_CITAS_TABLE
        					+ " WHERE " + CITAS_KEY_USED + " = 0";
		
        Log.d("FlowObjectDBAdapter",sqlQuery);
		
        Cursor c 		= db.rawQueryWithFactory(null, sqlQuery, null, null);
        int idChoosed 	= Utils.getRandom(c.getCount());
        c.close();
        Log.d("FlowObjectDBAdapter","Choosed random CitaId = " + idChoosed);
		
		return getCitaById(idChoosed);
	}		
	
	private void setUsed(int id){
		db.execSQL("UPDATE CITAS_TABLE set used= 1 where id = " + id);
        Log.d("FlowObjectDBAdapter","UPDATE CITAS_TABLE set used= 1 where id = " + id);
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
}
