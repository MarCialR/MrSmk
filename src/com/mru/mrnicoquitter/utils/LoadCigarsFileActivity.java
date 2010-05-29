package com.mru.mrnicoquitter.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.viewers.TextViewer;

public class LoadCigarsFileActivity extends ListActivity {
	

	private List<String> directoryEntries = new ArrayList<String>();
	File prefsDirectory = new File(
			"/data/data/com.mru.mrnicoquitter/shared_prefs/");

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		directoryEntries.add("cigars.cf");
		directoryEntries.add("cigars.json");
		directoryEntries.add("test.json");
        
		AppUtils.showDebug(getApplicationContext(), "LoadCigarsFileActivity - onCreate!!");

//		int currentPathStringLenght = cigarFilesDirectory.getAbsolutePath().length();
//
//		for (File file : cigarFilesDirectory.listFiles()) {
//			this.directoryEntries.add(file.getAbsolutePath().substring(
//					currentPathStringLenght));
//		}

		ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
				R.layout.cigar_row, this.directoryEntries);

		this.setListAdapter(directoryList);

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		String clickedFileLocation = /*this.prefsDirectory.getAbsolutePath()
				+ */this.directoryEntries.get(position);
		
		Intent i = new Intent(this, TextViewer.class);
		i.putExtra("clickedFileLocation", clickedFileLocation);


	    Resources res=null;
		try {
			res = createPackageContext("com.mru.mrnicoquitter", 0).getResources();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	    AssetManager assets = res.getAssets(); 

		List<Cigar> test = Utils.getAssetsCigarContents(clickedFileLocation, assets);
		CigarDBAdapter.getInstance(this).bulkInsert(test);
		AppUtils.showToastShort(this, "Done Loading Cigars");
	}
	
	
	//getRawResourceContent

}
