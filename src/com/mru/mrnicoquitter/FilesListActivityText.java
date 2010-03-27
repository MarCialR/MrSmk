package com.mru.mrnicoquitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mru.mrnicoquitter.ui.AppUtils;

public class FilesListActivityText extends ListActivity {

    private List<String> filesEntries = new ArrayList<String>(); 

	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        AppUtils.showDebug(getApplicationContext(), "PrefsList - onCreate!!");
        
        filesEntries.add("diez_mandam");
        
        
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, this.filesEntries); 
       
      this.setListAdapter(directoryList); 
        

    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) {

		TextView tv = (TextView)v;
		Intent i = new Intent( this, TextViewer.class );
		i.putExtra( "file", tv.getText());
		startActivity( i );

	}
    
}
