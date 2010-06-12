package com.mru.mrnicoquitter.ui;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LongToast extends Activity {

    private TextView toast_text;

    private ImageView image;

    private Toast toast;

    private View layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // your code ... most likely code that in some way triggers this toast

        toast = new Toast(this);

        // example of how to make a custom toast
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // define some kind of toast_layout.xml file in your res/layout directory and inflate it here
//        layout = inflater.inflate(R.layout.toast_layout, null);
//        image = (ImageView) layout.findViewById(R.id.image_icon);
//        toast_text = (TextView) layout.findViewById(R.id.toast_text);

        // programmatically set parameters
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        String text = "I'm a custom toast.";
        toast_text.setText(text);
//        image.setImageResource(R.icon);

        // and here is the hack
//        fireLongToast();
    }
//    private void fireLongToast() {
//    	mStopThread = false;
//    	//Log.d(LOG_TAG, “enter fireLongToast()”);
//    	Runnable long_toast_run = new Runnable() {
//    	@Override
//    	public void run() {
//    	try {
//    	while (!mStopThread) {
//    	mToast.show();
//    	Thread.sleep(2500);
//    	}
//    	} catch (Exception e) {
//    	Log.e(LOG_TAG, e.getMessage());
//    	}
//    	mHandler.post(new Runnable() {
//    	public void run() {
//    	mStopThread = true;
//    	mService.stopSelf();
//    	}
//    	});
//    	//Log.d(LOG_TAG, “exited long toast thread”);
//    	}
//    	};
//    	Thread t = new Thread(null, long_toast_run, LOG_TAG
//    	+ ” fireLongToast thread”);
//    	t.start();
//    	}
//    private void fireLongToast() {
//
//        Thread t = new Thread() {
//            public void run() {
//                int count = 0;
//                try {
//                    while (true && count < 10) {
//                        toast.show();
//                        sleep(1850);
//                        count++;
//
//                        // do some logic that breaks out of the while loop
//                    }
//                } catch (Exception e) {
//                    Log.e("LongToast", "", e);
//                }
//            }
//        };
//        t.start();
//    }
}
