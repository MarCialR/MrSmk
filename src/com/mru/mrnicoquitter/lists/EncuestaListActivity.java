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
import com.mru.mrnicoquitter.encuestas.EncuestaActivity;
import com.mru.mrnicoquitter.ui.AppUtils;

public class EncuestaListActivity extends ListActivity {
	
	
	
	

    private List<String> filesEntries = new ArrayList<String>(); 

	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        AppUtils.showDebug(getApplicationContext(), "FilesListActivity - onCreate!!");
        
        filesEntries.add("t_fagerstrom");
        filesEntries.add("t_glover_nilsson");
        filesEntries.add("t_posibilidades_exito");
        
        
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, this.filesEntries); 
       
      this.setListAdapter(directoryList); 
        

    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) {

		TextView tv = (TextView)v;
		Intent i = new Intent( this, EncuestaActivity.class );
		i.putExtra( "encuesta", tv.getText());
		startActivity( i );

	}
    
}