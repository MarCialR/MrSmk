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

import com.mru.mrnicoquitter.stage.Stage;
import com.mru.mrnicoquitter.stage.StageManagerSGTon;

public class FlowActivity extends Activity {

	private Stage stage;
	private OnClickListener goListener;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		stage = StageManagerSGTon.getStage(getApplicationContext());
		setContentView(buildView());
		TextView tipo = (TextView) this.findViewById(R.id.FlowText);
		tipo.setText(stage.getInfo());
		

		Button go = (Button) findViewById(R.id.Flow_GO_Button);
		goListener = new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),stage.getActivity()));
				finish();
			}
		};
		go.setOnClickListener(goListener);	

	}
	
	private View buildView(){
		//OJO con este this

		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout commonLyt = (LinearLayout)inflater.inflate(R.layout.flow, null);
		inflater.inflate(R.layout.flow_header, commonLyt,true);
		inflater.inflate(R.layout.flow_body, commonLyt,true);
		inflater.inflate(R.layout.flow_buttons, commonLyt,true);
		
//		ImageView logo = (ImageView)  commonLyt.findViewById(R.id.Logo);
//		logo.setBackgroundResource(R.drawable.etapa0);
		return commonLyt;
	}
	
}
