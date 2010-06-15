package com.mru.mrnicoquitter.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import static com.mru.mrnicoquitter.Global.*;

import com.mru.mrnicoquitter.beans.Cigar;
import com.mru.mrnicoquitter.beans.Day;
import com.mru.mrnicoquitter.db.CigarDBAdapter;
import com.mru.mrnicoquitter.db.CigarHistoricDBAdapter;

public class CigarListActivity extends ListActivity {

	private List<Cigar> cigarEntries 			= new ArrayList<Cigar>();
	private List<Day> dayEntries 				= new ArrayList<Day>();
	private List<ImageAndText> textImageEntries = new ArrayList<ImageAndText>();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		loadToday();
		loadHistoric();
		
		transformBOTH();
		ListAdapter IAA = new ImageAndTextListAdapter(this,textImageEntries );

		this.setListAdapter(IAA);
	}
	private void loadToday() {
		cigarEntries = CigarDBAdapter.getInstance().getAllEntries();
		Collections.sort(cigarEntries);
		Collections.reverse(cigarEntries);		
	}
/*		return db.query(DB_CIGARS_TABLE, new String[] {CIGARS_KEY_ID, CIGARS_KEY_DATE, CIGARS_KEY_TYPE},
				null, null, null, null, null);
		//null, null, null, null, "date DESC");
	}

	public Cursor getAllHistoricEntries () {
		return db.query(DB_CIGARS_H_TABLE, new String[] {CIGARS_H_KEY_ID, CIGARS_H_KEY_DAY, CIGARS_H_KEY_COUNT},*/
	private void loadHistoric() {
		CigarHistoricDBAdapter dba = CigarHistoricDBAdapter.getInstance(getApplicationContext());
		Cursor c = dba.getAllHistoricEntries();

		if (c.moveToFirst()) {
			do {
				Day day = new Day();
				day.setDayNumber(c.getInt(CIGARS_H_COL_DAY));
				day.setCigarCount(c.getInt(CIGARS_H_COL_COUNT));
				dayEntries.add(day);
			} while (c.moveToNext());
		}
		c.close();// TODO revisar cursores
		Collections.sort(dayEntries);
		Collections.reverse(dayEntries);
	}
	
	
	
	private void transformBOTH(){
		for (Day day: dayEntries){
			textImageEntries.add(new ImageAndText("0", day.getCigarCount() + " cigarrillos el " + day.getDayNumber() + "dia"));
		}
		for (Cigar cig: cigarEntries){
			textImageEntries.add(new ImageAndText(""+cig.getTipo(), cig.getDateStr() + " - " + cig.getTipo()));
		}
		return;
	}
	
//	
//	private void transform(){
//		int counter = 0;
//		Calendar hoy = Calendar.getInstance();
//		int dayHoy = hoy.get(Calendar.DAY_OF_YEAR);
//		int dayBefore = -1;
//		Calendar before = Calendar.getInstance();
//		int dayActual;
//		Calendar actual;
//		boolean primero = true;
//		boolean more= true;
//
//		for (Cigar cig: cigarEntries){
//
//			actual = (Calendar.getInstance()); 
//			actual.setTimeInMillis(cig.getDate().getTime());
//			dayActual = actual.get(Calendar.DAY_OF_YEAR); 
//			if (primero){
//				dayBefore = dayActual;
//				primero = false;
//			}else 
//				dayBefore = before.get(Calendar.DAY_OF_YEAR); 
//			
//			// Si el cigarro es de hoy se printa normalmente
//			if ( dayActual == dayHoy){
//				if (counter >0 && more){
//					textImageEntries.add(new ImageAndText("0", counter + " cigarrillos el " + DateUtils.calendarToString(before, DateUtils.FORMAT_YYYYMMDD)));
//					more = false;
//					
//				}
//				textImageEntries.add(new ImageAndText(""+cig.getTipo(), cig.getDateStr() + " - " + cig.getTipo()));
//
//			} else{
//				// Si cambiamos de dia printamos el dia anterior
//				if (dayBefore!= actual.get(Calendar.DAY_OF_YEAR)){
//					
//					textImageEntries.add(new ImageAndText("0", counter + " cigarrillos el " + DateUtils.calendarToString(before, DateUtils.FORMAT_YYYYMMDD)));
//					before.setTimeInMillis(actual.getTimeInMillis());
//					counter = 0;
//				} else{
//					before.setTimeInMillis(actual.getTimeInMillis());					
//				}
//			}
//			counter++;
//		}
//		return;
//	}	
	
	
/*
	private List<String> getStringList() {
		List<String> cigarStrings = new ArrayList<String>();
		for (Cigar cig : cigarEntries) {
			cigarStrings.add(cig.getDateStr() + " - " + cig.getTipo());
		}
		return cigarStrings;
	}
*/

	
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
	        int identif = res.getIdentifier("ty"+imageAndText.getImageUrl(), "drawable", PKG_BASE);
	        
	        
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
