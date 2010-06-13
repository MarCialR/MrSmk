package com.mru.mrnicoquitter;

import static com.mru.mrnicoquitter.Global.DEBUG;
import static com.mru.mrnicoquitter.Global.PREFS_GLOBAL;
import static com.mru.mrnicoquitter.Global.PREF_CREATED;
import static com.mru.mrnicoquitter.Global.SPLASH_DISPLAY_LENGHT;

import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.widget.Toast;

import com.mru.mrnicoquitter.beans.Cita;
import com.mru.mrnicoquitter.db.CitasDBAdapter;
import com.mru.mrnicoquitter.db.NewDataBaseHelper;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.utils.UIUtils;
import com.mru.mrnicoquitter.utils.Utils;

public class Splash extends Activity {

	// ===========================================================
	// Fields
	// ===========================================================

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
		FlowManagerSGTon.initManager(context);				///OJO el metodo devuelve perono louso

		Locale loc 			= Locale.getDefault();
		CitasDBAdapter ad 	= CitasDBAdapter.getInstance(context);
		Cita cita 			= ad.getRandomEntry();
		ad.close();
		//UIUtils.showToastLong(context, ad.getEntry(Utils.getRandom()));

		
		CharSequence text = Html.fromHtml(cita.getText() + "<br> <b>" + cita.getAuthor() + "</b>");
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		
		/*
		 * New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {

			public void run() {
				FlowManagerSGTon.forceNext();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//UIUtils.showToastShort(context, "creating " + PREFS_GLOBAL);
		globalPreferences.edit().putBoolean(PREF_CREATED, true).putBoolean(DEBUG, false).commit(); // Don't forget to commit your edits!!!		
//		forzarDEBUG();
	}

	
//	private void forzarDEBUG() {
//    Editor editor 		= globalPreferences.edit();
//	editor.putBoolean(DEBUG, false);
//	editor.commit();	
//}	


}