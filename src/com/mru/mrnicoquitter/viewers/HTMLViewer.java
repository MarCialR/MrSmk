package com.mru.mrnicoquitter.viewers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.Utils;

public class HTMLViewer extends Activity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.html_viewer);
		TextView textArea = (TextView) findViewById(R.id.HTMLArea);
		Intent i = getIntent();
		String text = Utils.getXXX(i, getApplicationContext());
		textArea.setText(Html.fromHtml(text));

	}

}