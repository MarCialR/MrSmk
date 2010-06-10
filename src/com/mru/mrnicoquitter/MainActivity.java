package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.lists.CigarListActivity;
import com.mru.mrnicoquitter.ui.AppUtils;

public class MainActivity extends QActivity {

	private static OnClickListener saveListener, listListener, olvidoListener;

	private static Button saveButton, listButton;
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
	@Override
	protected void onResume() {

		super.onResume();
		/*
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			android:id="@+id/Common" 
			android:orientation="vertical" android:layout_width="fill_parent"
			android:layout_height="fill_parent" android:gravity="center"
			android:background="@drawable/smoke40">

			<ImageView android:id="@+id/Logo" android:layout_width="wrap_content"
				android:layout_height="wrap_content" android:layout_marginTop="10px"
				android:layout_marginBottom="10px" />
				
			<TextView android:text="@+id/StageInfo" android:id="@+id/StageInfo" 
				android:layout_width="wrap_content" android:layout_height="wrap_content"
				android:layout_marginBottom="10px"/>
				

		</LinearLayout>
*/		
		View v = (View) findViewById(R.id.Common);
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout commonLyt = (LinearLayout)inflater.inflate(R.layout.lay_common, null);
	
		ImageView logo	= (ImageView) commonLyt.findViewById(R.id.Logo);
		TextView text	= (TextView) commonLyt.findViewById(R.id.StageInfo);
		logo.setBackgroundResource(phase.getLogo());
		text.setText(FlowManagerSGTon.getHeaderText());	
		v= commonLyt;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		

		//bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		//setContentView(R.layout.main_scrollview_tablelayout);
//		setContentView(R.layout.main_linear_layout);
		
		
									//OJO con este this
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setContentView(phase.getCommonLayout(inflater,R.layout.lay_content_main));
		
		tipo = (Spinner) this.findViewById(R.id.TypeSpinner);

		CausesAdapter myAdapter = CausesAdapterSGTon.getInstance(getApplicationContext()).getList();
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

				if (olvidoCheckBox.isChecked()) {
					TimePicker picker = (TimePicker) findViewById(R.id.CuandoFumadoPicker);
					c.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
					c.set(Calendar.MINUTE, picker.getCurrentMinute());
				}
				cigar.setDateStr(SDF.format(c.getTime()));
				cigar.setId((int) tipo.getSelectedItemId());
				
				CigarDBAdapter.inserttt(cigar);
				

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