package com.mru.mrnicoquitter.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;

import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;

public class AppUtils {
	
	public final static  void showToastShort(Context ctx, String message){
		showToast(ctx, message, Toast.LENGTH_SHORT);
	}

	public final static  void showToastLong(Context ctx, String message){
		showToast(ctx, message, Toast.LENGTH_LONG);
	}
	
	private final static  void showToast(Context ctx, String message, int duration){
		CharSequence text = message;
		Toast toast = Toast.makeText(ctx, text, duration);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
	
	public final static  void showDebug(Context ctx, String message){
		State state = StateManagerSGTon.getState(ctx);
		SharedPreferences prefs = state.getGlobalPreferences();
		
		if (prefs.getBoolean("debug", false))
			showToast(ctx, message, Toast.LENGTH_SHORT);
	}	

}
