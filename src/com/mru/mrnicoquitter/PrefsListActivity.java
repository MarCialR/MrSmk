package com.mru.mrnicoquitter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;
import com.mru.mrnicoquitter.ui.AppUtils;

public class PrefsListActivity extends ListActivity {

    private List<String> directoryEntries = new ArrayList<String>(); 
    private File currentDirectory = new File("/"); 
	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        AppUtils.showDebug(getApplicationContext(), "PrefsList - onCreate!!");
        
        //setContentView(R.layout.main);
        State state = StateManagerSGTon.getState(null);
        SharedPreferences statePrefs = state.getPreferences();
        SharedPreferences globalPrefs = state.getGlobalPreferences();        


        ArrayList<String> MOSTRSAR = new ArrayList<String>();
        MOSTRSAR.add("GLOBAL");
        MOSTRSAR.addAll(globalPrefs.getAll().keySet());
        MOSTRSAR.add("STATE");
        MOSTRSAR.addAll(statePrefs.getAll().keySet());        
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, MOSTRSAR); 
       
      this.setListAdapter(directoryList); 
    }
    
    
   
}
