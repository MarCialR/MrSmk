package com.mru.mrnicoquitter.viewers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.Utils;

public class TextViewer extends Activity {

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.text_viewer);
		TextView textArea 			= (TextView) findViewById(R.id.TextArea);

		Intent i 					= getIntent();
		String clickedFileLocation 	= i.getStringExtra("clickedFileLocation");
		String fullContents 		= i.getStringExtra("fullContents");
		String text 				= null;

		if (null!=fullContents && !"".equals(fullContents))
			text = fullContents;
		else
			text = Utils.getXXX(i, getApplicationContext());

		if (null!=text && !"".equals(text))
			textArea.setText(text);
		else
			textArea.setText(Utils.getPrefsContent(clickedFileLocation));
	}
}