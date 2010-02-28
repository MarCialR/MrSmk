package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mru.mrnicoquitter.basura.Cigar;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;
import com.mru.mrnicoquitter.db.MyDBAdapter;
import com.mru.mrnicoquitter.timer.NotificationService;
import com.mru.mrnicoquitter.ui.AppUtils;

public class MainActivity extends Activity {
	private OnClickListener saveListener, listListener, sendListener,
			olvidoListener, runListener;

	Button saveButton, listButton, sendButton;
	ToggleButton runButton;
	CheckBox olvidoCheckBox;

	Spinner tipo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tipo = (Spinner) this.findViewById(R.id.TypeSpinner);
		String[] s = getResources().getStringArray(R.array.cigars);

		CausesAdapter myAdapter = CausesAdapterSGTon.getInstance(
				getApplicationContext(), s).getList();
		tipo.setAdapter(myAdapter);

		olvidoCheckBox = (CheckBox) findViewById(R.id.olvidado);
		olvidoListener = new OnClickListener() {
			public void onClick(View v) {
				TimePicker picker = (TimePicker) findViewById(R.id.TimePicker);
				if (picker.getVisibility() == View.VISIBLE)
					picker.setVisibility(View.INVISIBLE);// invisible
				else
					picker.setVisibility(View.VISIBLE);// visible
			}
		};
		olvidoCheckBox.setOnClickListener(olvidoListener);

		saveButton = (Button) findViewById(R.id.SaveButton);
		saveListener = new OnClickListener() {
			public void onClick(View v) {
				Cigar cigar = new Cigar();
				olvidoCheckBox = (CheckBox) findViewById(R.id.olvidado);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss.SSS");
				if (olvidoCheckBox.isChecked()) {
					TimePicker picker = (TimePicker) findViewById(R.id.TimePicker);
					c.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
					c.set(Calendar.MINUTE, picker.getCurrentMinute());
				}
				cigar.setDateStr(sdf.format(c.getTime()));
				cigar.setId((int) tipo.getSelectedItemId());
				MyDBAdapter dba = MyDBAdapter
						.getInstance(getApplicationContext());
				dba.insertEntry(cigar);
				AppUtils.showToastShort(getApplicationContext(), "Cigarro guardado!");
			}
		};
		saveButton.setOnClickListener(saveListener);

		listButton = (Button) findViewById(R.id.ViewListButton);
		listListener = new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),
						CigarListActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		listButton.setOnClickListener(listListener);

		sendButton = (Button) findViewById(R.id.SendButton);
		sendListener = new OnClickListener() {
			public void onClick(View v) {
				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
						new String[] { "marcialemilio@gmail.com" });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"myCigarsList");
				String dbToSave = MyDBAdapter.getInstance(
						getApplicationContext()).getAllEntriesToSend();
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						dbToSave);
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		};
		sendButton.setOnClickListener(sendListener);

		runButton = (ToggleButton)findViewById(R.id.run); 
		runListener = new OnClickListener() { 
			public void onClick(View v) {
				try {

					// setup and start MyService
					{
						NotificationService nS = new NotificationService();
						nS.setMainActivity(MainActivity.this);
						Intent svc = new Intent(MainActivity.this.getApplicationContext(), NotificationService.class);
						startService(svc);
					}

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
			
// La unica puta forma qu eencontreeeeeee!!!!!			
//			final Context ctx = this;
//			Handler mHandler = new Handler();
//			Runnable makeToast = new Runnable() {
//			public void run() {
//				AppUtils.showToastShort(MainActivity.this, "300 waited!");
//				runButton.setChecked(true);
//			}
//			};
//			mHandler.postDelayed(makeToast, 2000);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent svc = new Intent(this, NotificationService.class);
	    stopService(svc);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Intent svc = new Intent(this, NotificationService.class);
	    stopService(svc);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Intent svc = new Intent(this, NotificationService.class);
	    stopService(svc);
	}

}