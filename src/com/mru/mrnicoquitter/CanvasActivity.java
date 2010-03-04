package com.mru.mrnicoquitter;

import com.mru.mrnicoquitter.ui.CustomDrawableView;

import android.app.Activity;
import android.os.Bundle;


public class CanvasActivity extends Activity {

	
	CustomDrawableView mCustomDrawableView;

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mCustomDrawableView = new CustomDrawableView(this);
	    
	    setContentView(mCustomDrawableView);
	}
}
