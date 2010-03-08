package com.mru.mrnicoquitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.db.MyDBAdapter;

public class CigarListActivity extends ListActivity {

	private List<Cigar> cigarEntries = new ArrayList<Cigar>();
	private List<ImageAndText> textImageEntries = new ArrayList<ImageAndText>();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// setContentView(R.layout.main);
		loadCigars();
		ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
				R.layout.cigar_row, getStringList());
		transform();
		ImageAndTextListAdapter IAA = new ImageAndTextListAdapter(this,textImageEntries );

		this.setListAdapter(IAA);
	}

	private void transform(){

		for (Cigar cig: cigarEntries){
			textImageEntries.add(new ImageAndText(""+cig.getTipo(), cig.getDateStr()));
		}
		return;
	}

	private List<String> getStringList() {
		List<String> cigarStrings = new ArrayList<String>();
		for (Cigar cig : cigarEntries) {
			cigarStrings.add(cig.getDateStr() + " - " + cig.getTipo());
		}
		return cigarStrings;
	}

	private void loadCigars() {
		MyDBAdapter dba = MyDBAdapter.getInstance(getApplicationContext());
		Cursor c = dba.getAllEntries();
		
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS");

		if (c.moveToFirst()) {
			do {
				Cigar cigar = new Cigar();
				cigar.setDateStr(c.getString(MyDBAdapter.COLUMN_DATE));
				cigar.setTipo(c.getInt(MyDBAdapter.COLUMN_TYPE));
				try {
					cigar.setDate(sdf.parse(c.getString(MyDBAdapter.COLUMN_DATE)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cigarEntries.add(cigar);
			} while (c.moveToNext());
		}
		Collections.sort(cigarEntries);
	}
	
	public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {
		List<ImageAndText> yyyyyyyyyyyyyyy;
	    public ImageAndTextListAdapter(Activity activity, List<ImageAndText> imageAndTexts) {
	        super(activity, 0, imageAndTexts);
	        yyyyyyyyyyyyyyy = imageAndTexts;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        //Activity activity = (Activity) getContext();
	        //LayoutInflater inflater = activity.getLayoutInflater();

	        // Inflate the views from XML
	        LinearLayout rowView = new LinearLayout(getApplicationContext());
	        ImageAndText imageAndText = getItem(position);

	        // Load the image and set it on the ImageView
	        ImageView imageView = new ImageView(getApplicationContext());
	        Resources res = getApplicationContext().getResources();
	        int identif = res.getIdentifier("ty"+imageAndText.getImageUrl(), "drawable", "com.mru.mrnicoquitter");
	        
	        
	        Drawable daw = res.getDrawable(identif);
	        imageView.setImageDrawable(daw);

	        // Set the text on the TextView
	        TextView textView = new TextView(getApplicationContext());
	        textView.setText(imageAndText.getText());
	        rowView.addView(imageView);
	        rowView.addView(textView);
	        return rowView;
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return yyyyyyyyyyyyyyy.size();
		}

	}
	
	public class ImageAndText {
	    private String imageUrl;
	    private String text;

	    public ImageAndText(String imageUrl, String text) {
	        this.imageUrl = imageUrl;
	        this.text = text;
	    }
	    public String getImageUrl() {
	        return imageUrl;
	    }
	    public String getText() {
	        return text;
	    }
	}
}
