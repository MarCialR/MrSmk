package com.mru.mrnicoquitter;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mru.mrnicoquitter.state.State;
import com.mru.mrnicoquitter.state.StateManagerSGTon;

public class PrefsListActivity extends ListActivity {


	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
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
