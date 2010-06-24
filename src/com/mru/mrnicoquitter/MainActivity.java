package com.mru.mrnicoquitter;

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
import com.mru.mrnicoquitter.cigars.DayManagerSGTon;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.flow.FlowManagerSGTon;
import com.mru.mrnicoquitter.lists.CigarListActivity;
import com.mru.mrnicoquitter.utils.UIUtils;

public class MainActivity extends QActivity {

	private static OnClickListener saveListener, listListener, olvidoListener;

	private static Button saveButton, listButton;
	private static CheckBox olvidoCheckBox;

	private static Spinner tipo;
	private static LinearLayout commonLyt;
	
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
		UIUtils .showToastShort("OnCreate");

		//bindService(new Intent(this, NotificationService.class),onService, BIND_AUTO_CREATE);
		//setContentView(R.layout.main_scrollview_tablelayout);
//		setContentView(R.layout.main_linear_layout);
		
		
									//OJO con este this
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		commonLyt =  (LinearLayout)phase.getCommonLayout(inflater,R.layout.lay_content_main);
		setContentView(commonLyt);
		
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
				olvidoCheckBox = (CheckBox) findViewById(R.id.olvidado);
				Calendar c = null;
				if (olvidoCheckBox.isChecked()) {
					c = Calendar.getInstance();
					TimePicker picker = (TimePicker) findViewById(R.id.CuandoFumadoPicker);
					c.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
					c.set(Calendar.MINUTE, picker.getCurrentMinute());
				}
				DayManagerSGTon.getInstance().insert(tipo.getSelectedItemId(), c);
				refresh();
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
	protected void onResume() {

		super.onResume();

		UIUtils .showToastShort("OnResume");
		View v = (View) findViewById(R.id.Common);
	
		ImageView logo	= (ImageView) commonLyt.findViewById(R.id.Logo);
		TextView text	= (TextView) commonLyt.findViewById(R.id.StageInfo);
		logo.setBackgroundResource(phase.getLogo());
		text.setText(FlowManagerSGTon.getHeaderText());	
		v= commonLyt;
		//refresh();
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
	private void refresh(){
		((TextView) 	commonLyt.findViewById(R.id.CigarCount)).setText(Integer.toString(CigarDBAdapter.getInstance().pubGetCount()));
		
	}


	




}