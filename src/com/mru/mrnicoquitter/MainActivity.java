package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mru.mrnicoquitter.basura.Cigar;
import com.mru.mrnicoquitter.db.MyDBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
    private OnClickListener saveListener, listListener, sendListener;
    //private OnItemSelectedListener itsel;
    Button saveButton,listButton, sendButton;

    Spinner tipo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        tipo = (Spinner)this.findViewById(R.id.TypeSpinner);
        String []s= getResources().getStringArray(R.array.cigars);

        MyListAdapter myAdapter = new MyListAdapter(getApplicationContext(), s);
        tipo.setAdapter(myAdapter);


        saveButton = (Button)findViewById(R.id.SaveButton);
        saveListener = new OnClickListener() {
            public void onClick(View v) {
            	Cigar cigar = new Cigar();
        		Calendar c = Calendar.getInstance();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            	cigar.setDate(sdf.format(c.getTime()));
            	Spinner i =MainActivity.this.tipo;
            	long id =i.getSelectedItemId();
            	int pos =i.getSelectedItemPosition();
            	Object obj =i.getSelectedItem();

            	cigar.setId((int)tipo.getSelectedItemId());
            	MyDBAdapter dba = new MyDBAdapter(getApplicationContext());
            	dba.insertEntry(cigar);
              }
          };
        saveButton.setOnClickListener(saveListener);
        

        listButton = (Button)findViewById(R.id.ViewListButton);
        listListener = new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), CigarListActivity.class);
                startActivityForResult(myIntent, 0);
              }
          };
        listButton.setOnClickListener(listListener);        
        
        sendButton = (Button)findViewById(R.id.SendButton);
        sendListener = new OnClickListener() {
            public void onClick(View v) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
                emailIntent.setType("plain/text"); 
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"marcialemilio@gmail.com"}); 
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "myCigarsList"); 
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "aqui iria"); 
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));            	
              }
          };
        sendButton.setOnClickListener(sendListener);          
        
    }
    
}