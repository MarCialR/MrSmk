package com.mru.mrnicoquitter;

import static com.mru.mrnicoquitter.Global.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import com.google.gson.Gson;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.Stage;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.db.flow.FlowObjectDBAdapter;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.stage.Phase;
import com.mru.mrnicoquitter.ui.AppUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	// ===========================================================
	// Fields
	// ===========================================================

	private Phase phase;
	private Context context;

	// ===========================================================
	// "Constructors"
	// ===========================================================

	/** Called when the activity is first created. */

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		context = getApplicationContext();
		
		SharedPreferences globalPreferences 	= context.getSharedPreferences(PREFS_GLOBAL, Context.MODE_PRIVATE);
//		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
//			initMrQuitter(globalPreferences);
//		}	
		initMrQuitter(globalPreferences);

		phase = FlowManagerSGTon.initManager(context);

		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {

			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				//Class<?> goTo = phase.getActiveClassToLaunch();
				 Class<?> goTo = null;
				try {
					goTo = Class.forName(ACVTY_MAIN_CLASS);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent mainIntent = new Intent(Splash.this, goTo);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();

			}
		}, SPLASH_DISPLAY_LENGHT);
		
		setContentView(R.layout.splash_screen);		
	}

	private void initMrQuitter(SharedPreferences globalPreferences) {
		InputStream fis 		= null;
		BufferedInputStream bis = null;
		DataInputStream dis 	= null;
		List<String> inserts 	= new ArrayList<String>();

		try {
			Resources res = getApplicationContext().getResources();
			fis = res.openRawResource(R.raw.flow_inserts);
			bis = new BufferedInputStream(fis);	// Here BufferedInputStream is added for fast reading.
			dis = new DataInputStream(bis);

			System.out.println("!!! FLOW INSERTS DETECTED !!!");			
			while (dis.available() != 0) {	// dis.available() returns 0 if the file does not have more lines.
				String line = dis.readLine();
				inserts.add(line);
			}

			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FlowObjectDBAdapter flowDB = FlowObjectDBAdapter.getInstance(getApplicationContext()).open();
		flowDB.cleanDB();
		flowDB.bulkInsert(inserts);
		flowDB.close();
		
		AppUtils.showToastShort(context, "creating " + PREFS_GLOBAL);
		globalPreferences.edit().putBoolean(PREF_CREATED, true).putBoolean(DEBUG, false).commit(); // Don't forget to commit your edits!!!		
		
//		forzarDEBUG();
	}
//	private void forzarDEBUG() {
//    Editor editor 		= globalPreferences.edit();
//	editor.putBoolean(DEBUG, false);
//	editor.commit();	
//}	
}