package com.mru.mrnicoquitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.lists.PrefsListActivity;
import com.mru.mrnicoquitter.timer.NotificationService;
import com.mru.mrnicoquitter.utils.LoadCigarsFileActivity;
import com.mru.mrnicoquitter.utils.UIUtils;


public class DevelopingActivity extends QActivity{


	private static OnClickListener   canvasButtonListener, timelineButtonListener, sendListener,
			 notificarOnOffListener, notificarListener, runListener, loadCigarFileListener;

	private static Button  canvasButton, timelineButton, sendButton, notifButton, loadCigarFileButton;
	private static ToggleButton runButton;
	private static CheckBox notificarCheckBox;
	
	private NotificationService appService	=	null;
	
	private ServiceConnection onService	= new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder rawBinder) {
			appService	=	((NotificationService.LocalBinder)rawBinder).getService();
		}
 
		public void onServiceDisconnected(ComponentName className) {
			appService	=	null;
		}
	};	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		//HA DADO PROBLEMAS
//		unbindService(onService);
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setContentView(phase.getCommonLayout(inflater,R.layout.lay_content_developing));		
		
//	       // Restore preferences
//		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//       //SharedPreferences settings = stage.getPreferences();
//       //Map<String,?> uu= settings.getAll();
//       boolean silent = settings.getBoolean("silentMode", false);
//       TextView messg = (TextView)findViewById(R.id.TextView01);
//       messg.setText(Boolean.valueOf(silent).toString());
//       prueba = !silent;			
//		


		final RadioGroup grup = (RadioGroup) findViewById(R.id.RadioGroup01);
		
		Button changeButton = (Button) findViewById(R.id.SetStateButton);
		
		OnClickListener changeStageListener = new OnClickListener() {
			public void onClick(View v) {
				
				String selected = ((Button) findViewById(grup.getCheckedRadioButtonId())).getText().toString();
				if (selected.equalsIgnoreCase(PHASE_1)) 
					phase = FlowManagerSGTon.setPhase(PHASE_1_CODE);
				else if (selected.equalsIgnoreCase(PHASE_2)) 
					phase = FlowManagerSGTon.setPhase(PHASE_2_CODE);
				else if (selected.equalsIgnoreCase(PHASE_3)) 
					phase = FlowManagerSGTon.setPhase(PHASE_3_CODE);
				else if (selected.equalsIgnoreCase(PHASE_4)) 
					phase = FlowManagerSGTon.setPhase(PHASE_4_CODE);		
			
				Intent i = new Intent(DevelopingActivity.this, DevelopingActivity.this.getClass());
				DevelopingActivity.this.startActivity(i);
				DevelopingActivity.this.finish();
			}
		};
		changeButton.setOnClickListener(changeStageListener);
		
		
		
		
		
		
		
		
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
					UIUtils.showToastShort(getApplicationContext(), "Exception en setNotif!");
				}
			}
		};
		notifButton.setOnClickListener(notificarListener);		
		

		
		loadCigarFileButton= (Button) findViewById(R.id.loadCigarFileButton);
		loadCigarFileListener = new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),LoadCigarsFileActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		loadCigarFileButton.setOnClickListener(loadCigarFileListener);		

	
	
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
				AA(v.getContext(), TimelineActivity.class,  0);

			}
		};
		timelineButton.setOnClickListener(timelineButtonListener);		
		
		
		
		
		sendButton = (Button) findViewById(R.id.SendButton);
		sendListener = new OnClickListener() {
			public void onClick(View v) {
				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
						exportDB();
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "marcial5@hotmail.com" });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"myCigarsList");
				emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory() + "/q.png"));
//				String dbToSave = CigarDBAdapter.getInstance().getAllEntriesToSendAsJSON();
//				Log.d("DevelopingActivity", dbToSave);
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		};
		sendButton.setOnClickListener(sendListener);

		runButton = (ToggleButton)findViewById(R.id.run); 
		runListener = new OnClickListener() { 
			public void onClick(View v) {
				try {
					if (runButton.isChecked())
						((ScrollView)v.getParent().getParent().getParent()).setBackgroundColor(Color.RED);
					else 
						((ScrollView)v.getParent().getParent().getParent()).setBackgroundColor(Color.TRANSPARENT);
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
			
			

	}
	private void exportDB(){

		String inputFileName 	= "data/data/com.mru.mrnicoquitter/databases/mrQuitter.db";
		String outFileName 		=  Environment.getExternalStorageDirectory() + "/q.png";   				// Path to the just created empty db
		InputStream myInput		= null;
    	OutputStream myOutput 	= null;

    	try {
			myInput 			= new FileInputStream(new File(inputFileName));
			myOutput 			= new FileOutputStream(new File( outFileName ));
	    	byte[] buffer 		= new byte[1024];   							//transfer bytes from the inputfile to the outputfile
	    	int length;

			while ((length = myInput.read(buffer))>0){
				myOutput.write(buffer, 0, length);
			}
			myOutput.flush();


		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	    	try {
				myOutput.close();
		    	myInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("DevelopingActivity", "Finished Exporting db to : " + outFileName);

	}
	@Override
	protected String[] getMandatoryFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isOKToLaunch() {
		// TODO Auto-generated method stub
		return false;
	}

}
