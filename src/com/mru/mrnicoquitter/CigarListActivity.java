package com.mru.mrnicoquitter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mru.mrnicoquitter.basura.Cigar;
import com.mru.mrnicoquitter.db.MyDBAdapter;

public class CigarListActivity extends ListActivity {

    private List<Cigar> cigarEntries = new ArrayList<Cigar>();
    
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.main);
        loadCigars();
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, getStringList()); 
       
      this.setListAdapter(directoryList); 
    }

    private List<String> getStringList(){
    	List<String> cigarStrings = new ArrayList<String>();
    	for (Cigar cig: cigarEntries){
    		cigarStrings.add(cig.getDate()+ " - " + cig.getTipo());
    	}
    	return cigarStrings;
    }

    private void loadCigars() {
    	MyDBAdapter dba = new MyDBAdapter(getApplicationContext());
    	Cursor c = dba.getAllEntries();
    	
        if (c.moveToFirst()){
        	do {
        		Cigar cigar = new Cigar();
        		cigar.setDate(c.getString(MyDBAdapter.COLUMN_DATE));
        		cigar.setTipo(c.getInt(MyDBAdapter.COLUMN_TYPE));
        		cigarEntries.add(cigar);
        	}while (c.moveToNext());
        }
		
	}
}
