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

import com.mru.mrnicoquitter.ui.AppUtils;

public class PrefsListActivityText extends ListActivity {

    private List<String> directoryEntries = new ArrayList<String>(); 
    File prefsDirectory = new File("/data/data/com.mru.mrnicoquitter/shared_prefs/");
	
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        AppUtils.showDebug(getApplicationContext(), "PrefsList - onCreate!!");
        
        
        File prefsDirectory = new File("/data/data/com.mru.mrnicoquitter/shared_prefs/");
        
        int currentPathStringLenght = prefsDirectory.getAbsolutePath().length(); 
        
        for (File file : prefsDirectory.listFiles()){ 
            this.directoryEntries.add(file.getAbsolutePath().substring(currentPathStringLenght)); 
       }         
        
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, 
                R.layout.cigar_row, this.directoryEntries); 
       
      this.setListAdapter(directoryList); 
        

    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) {

		
		String clickedFileLocation = this.prefsDirectory.getAbsolutePath() + this.directoryEntries.get(position);
		
		Intent i = new Intent( this, TextViewer.class );
		i.putExtra( "clickedFileLocation", clickedFileLocation );
		startActivity( i );
		

/*		
		File clickedFile = null;

		clickedFile = new File(this.prefsDirectory.getAbsolutePath()
				+ this.directoryEntries.get(position));

		FileReader fr;
		try {
			fr = new FileReader(clickedFile);

		BufferedReader br = new BufferedReader(fr);
		String s;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

*/
	}
    
}
