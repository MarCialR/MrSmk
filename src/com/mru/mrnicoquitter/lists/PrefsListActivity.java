package com.mru.mrnicoquitter.lists;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.viewers.TextViewer;

public class PrefsListActivity extends ListActivity {

	private List<String> directoryEntries = new ArrayList<String>();
	File prefsDirectory = new File(
			"/data/data/com.mru.mrnicoquitter/shared_prefs/");

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		AppUtils.showDebug(getApplicationContext(), "PrefsList - onCreate!!");

		File prefsDirectory = new File(
				"/data/data/com.mru.mrnicoquitter/shared_prefs/");

		int currentPathStringLenght = prefsDirectory.getAbsolutePath().length();

		for (File file : prefsDirectory.listFiles()) {
			this.directoryEntries.add(file.getAbsolutePath().substring(
					currentPathStringLenght));
		}

		ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
				R.layout.cigar_row, this.directoryEntries);

		this.setListAdapter(directoryList);

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		String clickedFileLocation = this.prefsDirectory.getAbsolutePath()
				+ this.directoryEntries.get(position);

		Intent i = new Intent(this, TextViewer.class);
		i.putExtra("clickedFileLocation", clickedFileLocation);
		startActivity(i);

	}

}
