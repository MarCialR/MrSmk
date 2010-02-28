package com.mru.mrnicoquitter.timer;

import java.util.Timer;
import java.util.TimerTask;

import com.mru.mrnicoquitter.MainActivity;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.ui.NotifListener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NotificationService extends Service {
	private static MainActivity MAIN_ACTIVITY;
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 3000;
	
	public static NotifListener NOTIF_LISTENER;
	public static void setUpdateListener(NotifListener l) {
		NOTIF_LISTENER = l;
		}
	public static void setMainActivity(MainActivity activity) {
		MAIN_ACTIVITY = activity;
	}

	/** not using ipc... dont care about this method */
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// init the service here
		_startService();

		if (MAIN_ACTIVITY != null)
			AppUtils.showToastShort(MAIN_ACTIVITY, "MyService started");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		_shutdownService();

		if (MAIN_ACTIVITY != null)
			AppUtils.showToastShort(MAIN_ACTIVITY.getApplicationContext(), "MyService stopped");
	}

	private void _startService() {
		timer.schedule(new TimerTask() {
			public void run() {
				AppUtils.showToastShort(MAIN_ACTIVITY.getApplicationContext(), "MyService running");
			}
		}, UPDATE_INTERVAL);
		Log.i(getClass().getSimpleName(), "Timer started!!!");
	}

	private void _shutdownService() {
		if (timer != null)
			timer.cancel();
		Log.i(getClass().getSimpleName(), "Timer stopped!!!");
	}

}