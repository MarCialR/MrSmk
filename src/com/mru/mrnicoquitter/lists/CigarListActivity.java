package com.mru.mrnicoquitter.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
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

	private void loadHistoric() {
		dayEntries = CigarHistoricDBAdapter.getInstance().getAllHistoricEntries();
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
