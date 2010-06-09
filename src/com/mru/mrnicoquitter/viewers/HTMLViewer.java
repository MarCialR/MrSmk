package com.mru.mrnicoquitter.viewers;

import android.os.Bundle;
import android.webkit.WebView;

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.QActivity;
import com.mru.mrnicoquitter.R;

public class HTMLViewer extends QActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String filename = getIntent().getStringExtra(STR_EXTRA_R_RAW_FILENAME);

		setContentView(R.layout.web_view);
		WebView temp_WebView = (WebView) findViewById(R.id.webview);
		temp_WebView.getSettings().setJavaScriptEnabled(true);
		temp_WebView.getSettings().setLoadsImagesAutomatically(true);
		temp_WebView.setBackgroundColor(R.color.black);
		temp_WebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		temp_WebView.loadUrl("file:///android_asset/" + filename + ".html");

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