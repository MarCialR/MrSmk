package com.mru.mrnicoquitter;

import java.util.ArrayList;
import java.util.Map;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PrefsListActivity extends ListActivity {

    SharedPreferences settings;
    Map<String,?> settingsMap;
	public static final String PREFS_NAME = "MyPrefsFile";
	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.main);
        settings = getSharedPreferences(PREFS_NAME, 0);
        settingsMap= settings.getAll();

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, new ArrayList<String>(settingsMap.keySet())); 
       
      this.setListAdapter(directoryList); 
    }
}
