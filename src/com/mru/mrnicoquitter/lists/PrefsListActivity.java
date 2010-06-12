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

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.UIUtils;
import com.mru.mrnicoquitter.viewers.TextViewer;

public class PrefsListActivity extends ListActivity {

	private List<String> directoryEntries = new ArrayList<String>();
	File prefsDirectory;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		UIUtils.showDebug(getApplicationContext(), "PrefsList - onCreate!!");

		prefsDirectory = new File(PREFERENCES_DIR);

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
		i.putExtra(STR_EXTRA_CLICKED_FILE_LOCATION, clickedFileLocation);
		i.putExtra(STR_EXTRA_NEWLAINEA, TRUE);
		startActivity(i);

	}

}
