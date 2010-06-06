package com.mru.mrnicoquitter;

import com.mru.mrnicoquitter.ui.CustomDrawableView;

import android.os.Bundle;


public class CanvasActivity extends QActivity {

	
	CustomDrawableView mCustomDrawableView;

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mCustomDrawableView = new CustomDrawableView(this);
	  //setContentView(R.layout.canvas);	
	    setContentView(mCustomDrawableView);
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
