package com.mru.mrnicoquitter.lists;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.viewers.HTMLViewer;

public class HTMLListActivity extends ListActivity {

    private List<String> filesEntries = new ArrayList<String>(); 

	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        AppUtils.showDebug(getApplicationContext(), "HTMLListActivity - onCreate!!");

        filesEntries.add("a1_bienvenida");
        filesEntries.add("a2_info_tabaquismo");
        filesEntries.add("a3_tests");
        filesEntries.add("diez_mandamientos");
        
        
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, this.filesEntries); 
       
      this.setListAdapter(directoryList); 
        

    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) {

		TextView tv = (TextView)v;
		Intent i = new Intent( getApplicationContext(), HTMLViewer.class );
		i.putExtra( "fileName", tv.getText());
		startActivity( i );

	}
    
}
