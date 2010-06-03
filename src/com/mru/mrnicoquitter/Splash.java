package com.mru.mrnicoquitter;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.stage.Stage;

import android.app.Activity;
import android.content.Intent;
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

		stage = FlowManagerSGTon.getStage(getApplicationContext());
		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen
		 * after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {

			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Class<?> goTo;
				// goTo = Class.forName(ACVTY_FLOW_CLASS);
				goTo = stage.getActiveClassToLaunch();
				Intent mainIntent = new Intent(Splash.this, goTo);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();

			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}