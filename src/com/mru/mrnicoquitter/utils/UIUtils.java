package com.mru.mrnicoquitter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Toast;

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.flow.FlowManagerSGTon;

public class UIUtils {
	
	
	public final static  void showToastShort(String message){
		showToast(FlowManagerSGTon.getAppContext(), message, Toast.LENGTH_SHORT);
	}
	
	public final static  void showToastShort(Context ctx, String message){
		showToast(ctx, message, Toast.LENGTH_SHORT);
	}

	public final static  void showToastLong(String message){
		showToast(FlowManagerSGTon.getAppContext(), message, Toast.LENGTH_LONG);
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
		SharedPreferences globalPrefs = FlowManagerSGTon.getGlobalPreferences();
		if (globalPrefs.getBoolean(DEBUG, false))
			showToast(ctx, message, Toast.LENGTH_SHORT);
	}	

	public static Drawable resizeImage(Context ctx, int resId, int w, int h) {

		  // load the origial Bitmap
		  Bitmap BitmapOrg = BitmapFactory.decodeResource(ctx.getResources(),
		                                                  resId);

		  int width = BitmapOrg.getWidth();
		  int height = BitmapOrg.getHeight();
		  int newWidth = w;
		  int newHeight = h;

		  // calculate the scale
		  float scaleWidth = ((float) newWidth) / width;
		  float scaleHeight = ((float) newHeight) / height;

		  // create a matrix for the manipulation
		  Matrix matrix = new Matrix();
		  // resize the Bitmap
		  matrix.postScale(scaleWidth, scaleHeight);
		  // if you want to rotate the Bitmap
		  // matrix.postRotate(45);

		  // recreate the new Bitmap
		  Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
		                                             width, height, matrix, true);

		  // make a Drawable from Bitmap to allow to set the Bitmap
		  // to the ImageView, ImageButton or what ever
		  return new BitmapDrawable(resizedBitmap);

		}
}
