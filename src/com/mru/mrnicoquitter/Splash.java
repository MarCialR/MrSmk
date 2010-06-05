package com.mru.mrnicoquitter;

import static com.mru.mrnicoquitter.Global.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.FlowItem;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.db.flow.FlowObjectDBAdapter;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.stage.Stage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	// ===========================================================
	// Fields
	// ===========================================================

	private Stage stage;

	// ===========================================================
	// "Constructors"
	// ===========================================================

	/** Called when the activity is first created. */

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash_screen);
		FlowManagerSGTon.initManager(getApplicationContext());
		if (FlowManagerSGTon.isFirstRun())
			initFlows();
		stage = FlowManagerSGTon.getStage();
		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {

			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Class<?> goTo = stage.getActiveClassToLaunch();
				// Class<?> goTo = Class.forName(ACVTY_FLOW_CLASS);
				Intent mainIntent = new Intent(Splash.this, goTo);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();

			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	private void initFlows() {
		InputStream fis 		= null;
		BufferedInputStream bis = null;
		DataInputStream dis 	= null;
		List<String> inserts 	= new ArrayList<String>();

		try {
			Resources res = getApplicationContext().getResources();
			fis = res.openRawResource(R.raw.flow_inserts);
			bis = new BufferedInputStream(fis);	// Here BufferedInputStream is added for fast reading.
			dis = new DataInputStream(bis);

			System.out.println("INSERTS DETECTED:\n");			
			while (dis.available() != 0) {	// dis.available() returns 0 if the file does not have more lines.
				String line = dis.readLine();
				inserts.add(line);
				System.out.println(line);
			}

			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FlowObjectDBAdapter FlowDB = FlowObjectDBAdapter.getInstance(getApplicationContext()).open();
		FlowDB.cleanDB();
		FlowDB.bulkInsert(inserts);
		FlowItem it = FlowDB.getEntry(1004);
		FlowDB.close();
		System.out.println("sdfs");
	}
}