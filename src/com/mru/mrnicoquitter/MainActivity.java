package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;
import com.mru.mrnicoquitter.db.MyDBAdapter;
import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;
import com.mru.mrnicoquitter.ui.AppUtils;

public class MainActivity extends Activity {
	private State state;
	private static OnClickListener saveListener, listListener, olvidoListener, developingListener;

	private static Button saveButton, listButton, developingButton;
	private static CheckBox olvidoCheckBox;

	private static Spinner tipo;
	
//	private NotificationService appService=null;
//	
//	private ServiceConnection onService = new ServiceConnection() {
//		public void onServiceConnected(ComponentName className, IBinder rawBinder) {
//			appService=((NotificationService.LocalBinder)rawBinder).getService();
//		}
// 
//		public void onServiceDisconnected(ComponentName className) {
//			appService=null;
//		}
//	};	
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		AppUtils.showDebug(getApplicationContext(), "Main - onCreate!!");
		
		state = StateManagerSGTon.getState(getApplicationContext());
		//bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		//setContentView(R.layout.main_scrollview_tablelayout);
//		setContentView(R.layout.main_linear_layout);
		
		
									//OJO con este this
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setContentView(state.getCommonLayout(inflater,R.layout.lay_content_main));
		
		tipo = (Spinner) this.findViewById(R.id.TypeSpinner);
		String[] s = getResources().getStringArray(R.array.cigars);
		CausesAdapter myAdapter = CausesAdapterSGTon.getInstance(getApplicationContext(), s).getList();
		tipo.setAdapter(myAdapter);

		olvidoCheckBox = (CheckBox) findViewById(R.id.olvidado);
		olvidoListener = new OnClickListener() {
			public void onClick(View v) {
				TimePicker picker = (TimePicker) findViewById(R.id.CuandoFumadoPicker);
				if (picker.getVisibility() == View.VISIBLE)
					picker.setVisibility(View.GONE);// invisible
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
					TimePicker picker = (TimePicker) findViewById(R.id.CuandoFumadoPicker);
					c.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
					c.set(Calendar.MINUTE, picker.getCurrentMinute());
				}
				cigar.setDateStr(sdf.format(c.getTime()));
				cigar.setId((int) tipo.getSelectedItemId());
				MyDBAdapter dba = MyDBAdapter
						.getInstance(getApplicationContext());
				dba.insertEntry(cigar);
				AppUtils.showToastShort(getApplicationContext(),
						"Cigarro guardado!");
			}
		};

		saveButton.setOnClickListener(saveListener);		

		listButton = (Button) findViewById(R.id.ViewListButton);
		listListener = new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),CigarListActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};
		listButton.setOnClickListener(listListener);

		
		
		developingButton = (Button) findViewById(R.id.DevelopingButton);
		developingListener = new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),DevelopingActivity.class);
				startActivityForResult(myIntent, 0);
			}
		};

		developingButton.setOnClickListener(developingListener);	

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AppUtils.showDebug(getApplicationContext(), "Main - onDestroy!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		AppUtils.showDebug(getApplicationContext(), "Main - onPause!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		AppUtils.showDebug(getApplicationContext(), "Main - onRestart!!");
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		AppUtils.showDebug(getApplicationContext(), "Main - onStop!!");

	}
	public static void doNothing(){
		return;
		
	}

}