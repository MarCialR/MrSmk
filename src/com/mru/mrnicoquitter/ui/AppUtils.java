package com.mru.mrnicoquitter.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class AppUtils {
	
	public final static  void showToastShort(Context ctx, String message){
		showToast(ctx, message, Toast.LENGTH_SHORT);
	}

	public final static  void showToastLong(Context ctx, String message){
		showToast(ctx, message, Toast.LENGTH_LONG);
	}
	
	public final static  void showToast(Context ctx, String message, int duration){
		CharSequence text = message;
		Toast toast = Toast.makeText(ctx, text, duration);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

}
