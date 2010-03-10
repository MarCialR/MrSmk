package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;
import com.mru.mrnicoquitter.db.MyDBAdapter;
import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;
import com.mru.mrnicoquitter.timer.NotificationService;
import com.mru.mrnicoquitter.ui.AppUtils;

public class MainActivity extends Activity {
	private State state;
	private OnClickListener saveListener, listListener, olvidoListener, developingListener;

	Button saveButton, listButton, developingButton;
	CheckBox olvidoCheckBox;

	Spinner tipo;

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
		//state = StateManagerSGTon.getState(getApplicationContext());
		//bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		setContentView(R.layout.main_scrollview_tablelayout);
		
		
	
		
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
//		Intent svc = new Intent(this, NotificationService.class);
//	    stopService(svc);
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

	}

}