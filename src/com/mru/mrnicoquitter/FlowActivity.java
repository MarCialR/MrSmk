package com.mru.mrnicoquitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mru.mrnicoquitter.flow.FlowManagerSGTon;

import com.mru.mrnicoquitter.stage.Phase;


public class FlowActivity extends Activity {

	private Phase phase;
	private OnClickListener prevListener, goListener, nextListener;
	private TextView info;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		phase = FlowManagerSGTon.getPhase();
		setContentView(buildView());
		info = (TextView) this.findViewById(R.id.FlowText);
		
		info.setText(FlowManagerSGTon.getInfo());

		
		//			PREVIOUS BUTTON
		prevListener = new OnClickListener() {
			public void onClick(View v) {
				FlowManagerSGTon.prev();
				info.setText(FlowManagerSGTon.getInfo());				
			}
		};
		((Button) findViewById(R.id.Flow_PREV_Button)).setOnClickListener(prevListener);

		
		//			GO BUTTON
		goListener = new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),FlowManagerSGTon.class));//getActiveClassToLaunch()));
				finish();
			}
		};
		((Button) findViewById(R.id.Flow_GO_Button)).setOnClickListener(goListener);

		
		//			NEXT BUTTON
		nextListener = new OnClickListener() {
			public void onClick(View v) {
				FlowManagerSGTon.next();
				info.setText(FlowManagerSGTon.getInfo());			
			}
		};
		((Button) findViewById(R.id.Flow_NEXT_Button)).setOnClickListener(nextListener);

	}
	
	private View buildView(){
		
		//OJO con este this
		LayoutInflater inflater	= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout commonLyt	= (LinearLayout)inflater.inflate(R.layout.flow, null);
		inflater.inflate(R.layout.flow_header, commonLyt,true);
		inflater.inflate(R.layout.flow_body, commonLyt,true);
		inflater.inflate(R.layout.flow_buttons, commonLyt,true);
		
//		ImageView logo = (ImageView)  commonLyt.findViewById(R.id.Logo);
//		logo.setBackgroundResource(R.drawable.etapa0);
		return commonLyt;
	}
	
}
