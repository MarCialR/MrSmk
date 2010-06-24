package com.mru.mrnicoquitter;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.beans.Cita;
import com.mru.mrnicoquitter.cigars.DayManagerSGTon;
import com.mru.mrnicoquitter.db.CitasDBAdapter;
import com.mru.mrnicoquitter.db.NewDataBaseHelper;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.UIUtils;

public class Splash extends Activity {

	private Context context;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		context = getApplicationContext();
		FlowManagerSGTon.initManager(context);
		
		SharedPreferences globalPreferences 	= context.getSharedPreferences(PREFS_GLOBAL, Context.MODE_PRIVATE);
		if (!globalPreferences.getBoolean(PREF_CREATED,false)){
			initMrQuitter(globalPreferences);
		}	
//		initMrQuitter(globalPreferences);
	

		Cita cita	= CitasDBAdapter.getInstance().getRandomEntry();
		
		CharSequence text 	= Html.fromHtml(cita.getText() + "<br/><br/> <b>" + cita.getAuthor() + "</b>");
		Toast toast 		= Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		
		new Handler().postDelayed(new Runnable() {		//New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.

			public void run() {
//				FlowManagerSGTon.forceNext();
				Splash.this.startActivity(FlowManagerSGTon.getIntent(Splash.this));
				Splash.this.finish();

			}
		}, SPLASH_DISPLAY_LENGHT);
		
		setContentView(R.layout.splash_screen);		
	}

	private void initMrQuitter(SharedPreferences globalPreferences) {
		NewDataBaseHelper dbH = new NewDataBaseHelper(context);
		try {
			dbH.createDataBase();
			dbH.close();
			UIUtils.showToastLong("DATABASE CREATED");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Splash","creating " + PREFS_GLOBAL);
		globalPreferences.edit().putBoolean(PREF_CREATED, true).putBoolean(DEBUG, false).commit(); // Don't forget to commit your edits!!!
		
		DayManagerSGTon.createPreferences();
	}

}