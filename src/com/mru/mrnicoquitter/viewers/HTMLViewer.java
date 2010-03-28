package com.mru.mrnicoquitter.viewers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.Utils;

public class HTMLViewer extends Activity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
	
		
		/*
		Uri uri = Uri.parse("file:///com.mru.mrnicoquitter/resources/raw/test.html");
		Intent intent = new Intent();
		intent.setData(uri);
		//intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
		intent.setClassName("com.android.htmlviewer", "com.android.htmlviewer.HTMLViewerActivity");
		startActivity(intent);		
		*/


        String htmName = getIntent().getStringExtra("fileName");
if (htmName.equals("test")){
        int id = Utils.getId(htmName,getApplicationContext());
		
		
		//KindaWorking
		setContentView(R.layout.web_view); 
        WebView temp_WebView = (WebView) findViewById(R.id.webview); 
        temp_WebView.getSettings().setJavaScriptEnabled(true); 
        temp_WebView.loadData(Utils.getRawResourceContentById(id, getApplicationContext()), "text/html", "utf-8"); 
}else if (htmName.equals("assets")){
	
	setContentView(R.layout.web_view); 
    WebView temp_WebView = (WebView) findViewById(R.id.webview); 
    temp_WebView.getSettings().setJavaScriptEnabled(true); 
    temp_WebView.loadUrl("file:///android_asset/test.html");
     
	
}else        

        
        //temp_WebView.loadData("test", "text/html", "utf-8");
        //temp_WebView.loadData(Utils.getRawResourceContentById(Utils.getId(htmName,getApplicationContext()), getApplicationContext()), "text/html", "utf-8"); 
        //temp_WebView.addView(WebView.inflate(getApplicationContext(), R.raw.test, null));//loadUrl("http://www.yahoo.com"); 
		
/* WORKING		
		*/
	
	{setContentView(R.layout.html_viewer);
		TextView textArea = (TextView) findViewById(R.id.HTMLArea);
		Intent i = getIntent();
		String text = Utils.getXXX(i, getApplicationContext());
//		textArea.setText(Html.fromHtml(text, null, new MrHTMLTagHandler()));
	textArea.setText(Html.fromHtml(text));
	}

	}

}