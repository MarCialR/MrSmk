package com.mru.mrnicoquitter.viewers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.QActivity;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.Utils;

public class TextViewer extends QActivity {

	private TextView textArea;
	
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		Intent i 					= getIntent();
		// Por Orden De Importancia, el primero que venga informado se muestra 
		String fullContents 		= i.getStringExtra(STR_EXTRA_FULL_CONTENTS_TO_SHOW);	// Texto a pelo		
		String filename		 		= i.getStringExtra(STR_EXTRA_R_RAW_FILENAME);			// Lo busca en R.raw
		String clickedFileLocation 	= i.getStringExtra(STR_EXTRA_CLICKED_FILE_LOCATION);	// indica el path completo	
		
		setContentView(R.layout.text_viewer);
		textArea 					= (TextView) findViewById(R.id.TextArea);
		String text 				= null;
		if (null!=fullContents && !"".equals(fullContents))
			text = fullContents;
		else if (null!=filename && !"".equals(filename)){
			text = Utils.getRawResourceContentByName(filename, getApplicationContext());
		} else if (null!=clickedFileLocation && !"".equals(clickedFileLocation))
			text = Utils.getPrefsContent(clickedFileLocation);
		else
			throw new RuntimeException();

		textArea.setText(text);
	}

	@Override
	protected boolean isOKToLaunch() {
		return true;
		
	}

	@Override
	protected String[] getMandatoryFields() {
		return  new String[] {STR_EXTRA_CLICKED_FILE_LOCATION};
	}
}