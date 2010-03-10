package com.mru.mrnicoquitter.timer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.mru.mrnicoquitter.MainActivity;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;

public class NotificationService extends Service {
	private NotificationManager notificationManager;	
	private static MainActivity MAIN_ACTIVITY;
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 3000;
	private final Binder binder=new LocalBinder();
	synchronized public void setNotification(String notifText, int segsToWait) {
		setToast(notifText,segsToWait);
	}
	
	public class LocalBinder extends Binder {
		public NotificationService getService() {
			return(NotificationService.this);
		}
	}
	private void setToast(String s, int e){
		// La unica puta forma qu eencontreeeeeee!!!!!			

		
		String svcName = Context.NOTIFICATION_SERVICE;

		notificationManager = (NotificationManager)getSystemService(svcName);		
		
		// Choose a drawable to display as the status bar icon
		int icon = R.drawable.no_smoking_icon;
		// Text to display in the status bar when the notification is launched
		String tickerText = "Notification";
		// The extended status bar orders notification in time order
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		Context context = getApplicationContext();
		// Text to display in the extended status window
		String expandedText = "Extended status text";
		// Title for the expanded status
		String expandedTitle = "Notification Title";
		// Intent to launch an activity when the extended text is clicked
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent launchIntent = PendingIntent.getActivity(context, 0, intent, 0);
		notification.setLatestEventInfo(context,expandedTitle,
				expandedText,
				launchIntent);
//		long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };
//		notification.vibrate = vibrate;
		int notificationRef = 1;
		notificationManager.notify(notificationRef, notification);		
		
		
		
		
		
		Handler mHandler = new Handler();
		Runnable makeToast = new MyRunnable(s,e);

		mHandler.postDelayed(makeToast, e*1000);
	}

	private class MyRunnable implements Runnable {
		 
		  private int value1;
		  private String value2;
		 
		  public MyRunnable(String s, int e) {
		    value1 = e;
		    value2 = s;}
		 
		  public void run() { //implement run using value1 and value2
				AppUtils.showToastShort(getApplicationContext(), this.value2);
				String svcName = Context.NOTIFICATION_SERVICE;

				notificationManager = (NotificationManager)getSystemService(svcName);	

				notificationManager.cancel(1);
				
				// Choose a drawable to display as the status bar icon
				int icon = R.drawable.smoking_icon_transp;
				// Text to display in the status bar when the notification is launched
				String tickerText = "Notification";
				// The extended status bar orders notification in time order
				long when = System.currentTimeMillis();
				Notification notification = new Notification(icon, tickerText, when);
				
				Context context = getApplicationContext();
				// Text to display in the extended status window
				String expandedText = "Extended status text";
				// Title for the expanded status
				String expandedTitle = "Notification Title";
				// Intent to launch an activity when the extended text is clicked
				Intent intent = new Intent(NotificationService.this, MainActivity.class);
				PendingIntent launchIntent = PendingIntent.getActivity(context, 0, intent, 0);
				notification.setLatestEventInfo(context,expandedTitle,
						expandedText,
						launchIntent);
				long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };
				notification.vibrate = vibrate;
				int notificationRef = 1;
				notificationManager.notify(notificationRef, notification);					
				
				
		  }
		}
	
//	
//	public static NotifListener NOTIF_LISTENER;
//	public static void setUpdateListener(NotifListener l) {
//		NOTIF_LISTENER = l;
//		}
	public static void setMainActivity(MainActivity activity) {
		MAIN_ACTIVITY = activity;
	}

	/** not using ipc... dont care about this method */
	public IBinder onBind(Intent intent) {
		return(binder);
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