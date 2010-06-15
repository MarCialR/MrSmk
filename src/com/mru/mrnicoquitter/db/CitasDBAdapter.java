package com.mru.mrnicoquitter.db;

import com.mru.mrnicoquitter.beans.Cita;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.Utils;

import static com.mru.mrnicoquitter.Global.*;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class CitasDBAdapter {

	private final Context context;				// Context of the application using the database.
	private NewDataBaseHelper dbHelper;			// Database open/upgrade helper	
	
	private static CitasDBAdapter INSTANCE;

	
	private Cita getCitaById(int _rowIndex, SQLiteDatabase db) {

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
		setUsed(db, _rowIndex);			
		db.close();

		return cita;
	}	
	public Cita getRandomEntry() {

        String sqlQuery = 	  "SELECT " + CITAS_KEY_ID
        					+ " FROM " + DB_CITAS_TABLE
        					+ " WHERE " + CITAS_KEY_USED + " = 0";
		
        Log.d("FlowObjectDBAdapter",sqlQuery);
		
        SQLiteDatabase db 	= dbHelper.getWritableDatabase();
        Cursor c 			= db.rawQueryWithFactory(null, sqlQuery, null, null);
        int idChoosed 		= Utils.getRandom(c.getCount());
        c.close();
        Log.d("FlowObjectDBAdapter","Choosed random CitaId = " + idChoosed);
        Cita cita 			= getCitaById(idChoosed, db); 
		db.close();
		return cita;
	}		
	
	private void setUsed(SQLiteDatabase db, int id){
		db.execSQL("UPDATE CITAS_TABLE set used= 1 where id = " + id);
        Log.d("FlowObjectDBAdapter","UPDATE CITAS_TABLE set used= 1 where id = " + id);
	}
	
	public static CitasDBAdapter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CitasDBAdapter(FlowManagerSGTon.getAppContext());
		}
		return INSTANCE;
	}

	private CitasDBAdapter(Context _context) {
		context 	= _context;
		dbHelper 	= new NewDataBaseHelper(context);
	}

}
