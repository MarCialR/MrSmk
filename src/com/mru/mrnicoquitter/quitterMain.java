package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mru.mrnicoquitter.basura.MyObject;
import com.mru.mrnicoquitter.db.MyDBAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class quitterMain extends Activity {
    private OnClickListener mCorkyListener;
    private OnItemSelectedListener itsel;
    Button saveButton;
    Spinner tipo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        tipo = (Spinner)this.findViewById(R.id.TypeSpinner);
        String []s= {"text","rrrr"};

        MyListAdapter myAdapter = new MyListAdapter(getApplicationContext(), s);
        tipo.setAdapter(myAdapter);

    	MyDBAdapter dba = new MyDBAdapter(getApplicationContext());
    	Cursor c = dba.getAllEntries();
        if (c.moveToFirst()){
        	do {
        		String d = c.getString(1);
        		System.out.println(s);
        	}while (c.moveToNext());
        }
        saveButton = (Button)findViewById(R.id.SaveButton);
        itsel = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		};
        mCorkyListener = new OnClickListener() {
            public void onClick(View v) {
            	MyObject obj = new MyObject();
        		Calendar c = Calendar.getInstance();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            	obj.setDate(sdf.format(c.getTime()));
            	ViewParent vp = v.getParent();
            	 Object i =quitterMain.this.tipo;
            	

            	obj.setId((int)tipo.getSelectedItemId());
            	MyDBAdapter dba = new MyDBAdapter(getApplicationContext());
            	dba.insertEntry(obj);
              }
          };
        saveButton.setOnClickListener(mCorkyListener);
        
        // Create an anonymous implementation of OnClickListener
    }
    
}