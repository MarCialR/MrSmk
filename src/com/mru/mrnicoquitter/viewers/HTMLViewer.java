package com.mru.mrnicoquitter.viewers;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mru.mrnicoquitter.R;

public class HTMLViewer extends Activity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String filename = getIntent().getStringExtra("fileName");

		setContentView(R.layout.web_view);
		WebView temp_WebView = (WebView) findViewById(R.id.webview);
		temp_WebView.getSettings().setJavaScriptEnabled(true);
		temp_WebView.getSettings().setLoadsImagesAutomatically(true);
		temp_WebView.setBackgroundColor(R.color.dark);
		temp_WebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		temp_WebView.loadUrl("file:///android_asset/" + filename + ".html");

	}

}