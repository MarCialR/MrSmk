package com.mru.mrnicoquitter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.mru.mrnicoquitter.db.MyDBAdapter;
import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;
import com.mru.mrnicoquitter.timer.NotificationService;
import com.mru.mrnicoquitter.ui.AppUtils;

public class DevelopingActivity extends Activity{

	private State state;
	private OnClickListener  prefsListListener, canvasButtonListener, timelineButtonListener, sendListener,
			 notificarOnOffListener, notificarListener, runListener, encuestaButtonListener;

	Button prefsListButton, canvasButton, timelineButton, sendButton, notifButton, encuestaButton;
	ToggleButton runButton;
	CheckBox notificarCheckBox;



	public static final String PREFS_NAME = "MyPrefsFile";
	
	private NotificationService appService=null;
	
	private ServiceConnection onService=new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder rawBinder) {
			appService=((NotificationService.LocalBinder)rawBinder).getService();
		}
 
		public void onServiceDisconnected(ComponentName className) {
			appService=null;
		}
	};	
	
private boolean prueba;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		state = StateManagerSGTon.getState(this);
		bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		setContentView(R.layout.developing);
		
		
//	       // Restore preferences
//		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//       //SharedPreferences settings = state.getPreferences();
//       //Map<String,?> uu= settings.getAll();
//       boolean silent = settings.getBoolean("silentMode", false);
//       TextView messg = (TextView)findViewById(R.id.TextView01);
//       messg.setText(Boolean.valueOf(silent).toString());
//       prueba = !silent;			
//		


		TimePicker picker = (TimePicker) findViewById(R.id.EsperarPicker);
		picker.setCurrentHour(0);
		picker.setCurrentMinute(0);
		
		notificarCheckBox = (CheckBox) findViewById(R.id.notificarCheck);
		notificarOnOffListener = new OnClickListener() {
			public void onClick(View v) {
				TimePicker picker = (TimePicker) findViewById(R.id.EsperarPicker);
				if (picker.getVisibility() == View.VISIBLE)
					picker.setVisibility(View.GONE);// invisible
				else
					picker.setVisibility(View.VISIBLE);// visible
			}
		};
		notificarCheckBox.setOnClickListener(notificarOnOffListener);		

		
		notifButton = (Button) findViewById(R.id.NotifButton);
		notificarListener = new OnClickListener() {
			public void onClick(View v) {

				TimePicker picker = (TimePicker) findViewById(R.id.EsperarPicker);
				try {
					Integer segundosAEsperar = (picker.getCurrentHour()-1)*60 + picker.getCurrentMinute();
					appService.setNotification("ALE! Ya puedes DAL-LE!",segundosAEsperar);
				}
				catch (final Throwable t) {
					AppUtils.showToastShort(getApplicationContext(), "Exception en setNotif!");
				}
			}
		};
		notifButton.setOnClickListener(notificarListener);		
		
		prefsListButton = (Button) findViewById(R.id.PrefsListButton);
		prefsListListener = new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),PrefsListActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		prefsListButton.setOnClickListener(prefsListListener);

		
		
		canvasButton = (Button) findViewById(R.id.CanvasButton);
		canvasButtonListener= new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),CanvasActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		canvasButton.setOnClickListener(canvasButtonListener);		
		
		

		timelineButton = (Button) findViewById(R.id.TimelineButton);
		timelineButtonListener= new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),TimelineActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		timelineButton.setOnClickListener(timelineButtonListener);		
		
		
		
		
		sendButton = (Button) findViewById(R.id.SendButton);
		sendListener = new OnClickListener() {
			public void onClick(View v) {
				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "marcialemilio@gmail.com" });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"myCigarsList");
				String dbToSave = MyDBAdapter.getInstance(getApplicationContext()).getAllEntriesToSend();
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,dbToSave);
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		};
		sendButton.setOnClickListener(sendListener);

		runButton = (ToggleButton)findViewById(R.id.run); 
		runListener = new OnClickListener() { 
			public void onClick(View v) {
				try {

//					{
//						NotificationService nS = new NotificationService();
//						nS.setMainActivity(MainActivity.this);
//						Intent svc = new Intent(MainActivity.this.getApplicationContext(), NotificationService.class);
//						startService(svc);
//					}

				}
				catch (Exception e) {
					Log.e("TAG", "ui creation problem", e);
				}
//				Timer t = new Timer(true);
//				t.schedule(new TimerTask() {
//					public void run() {
////						AppUtils.showToastShort(MainActivity.this, "300 waited!");
//						runButton.setChecked(false);
//					}
//				}, 3000);
			} };
			runButton.setOnClickListener(runListener);
			

			encuestaButton= (Button) findViewById(R.id.EncuestaButton);
			encuestaButtonListener= new OnClickListener() {
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),EncuestaActivity.class);
					startActivityForResult(myIntent, 0);
				}
			};
			encuestaButton.setOnClickListener(encuestaButtonListener);				
			

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	    unbindService(onService);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	      // Save user preferences. We need an Editor object to
	      // make changes. All objects are from android.context.Context
	      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	      SharedPreferences.Editor editor = settings.edit();
	      editor.putBoolean("silentMode", prueba);

	      // Don't forget to commit your edits!!!
	      editor.commit();
	}

}