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

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.EncuestaActivity;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.utils.UIUtils;

public class EncuestaListActivity extends ListActivity {

	private List<String> filesEntries = new ArrayList<String>();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		UIUtils.showDebug(getApplicationContext(),
				"FilesListActivity - onCreate!!");

		filesEntries.add("t_fagerstrom");
		filesEntries.add("t_glover_nilsson");
		filesEntries.add("t_motivacion");

		ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
				R.layout.cigar_row, this.filesEntries);

		this.setListAdapter(directoryList);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		TextView tv = (TextView) v;
		Intent i = new Intent(this, EncuestaActivity.class);
		i.putExtra(STR_EXTRA_ENCUESTA_NAME, tv.getText());
		startActivity(i);

	}

}
