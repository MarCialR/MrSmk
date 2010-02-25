package com.mru.mrnicoquitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.mru.mrnicoquitter.basura.Cigar;
import com.mru.mrnicoquitter.db.MyDBAdapter;
import com.mru.mrnicoquitter.db.CausesAdapter;
import com.mru.mrnicoquitter.db.CausesAdapterSGTon;

public class MainActivity extends Activity {
    private OnClickListener saveListener, listListener, sendListener, olvidoListener;
    //private OnItemSelectedListener itsel;
    Button saveButton,listButton, sendButton;
    RadioButton olvidoRadioButton;

    Spinner tipo;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        tipo = (Spinner)this.findViewById(R.id.TypeSpinner);
        String []s= getResources().getStringArray(R.array.cigars);

        CausesAdapter myAdapter = CausesAdapterSGTon.getInstance(getApplicationContext(), s).getList();
        tipo.setAdapter(myAdapter);


        olvidoRadioButton = (RadioButton)findViewById(R.id.olvidado);
        olvidoListener = new OnClickListener() {
			
			public void onClick(View v) {
				TimePicker picker = (TimePicker)findViewById(R.id.TimePicker);
				if (picker.getVisibility() == 0)
					picker.setVisibility(1);//invisible
				else
					picker.setVisibility(0);//visible
			}
		};
		olvidoRadioButton.setOnClickListener(olvidoListener);
        
        saveButton = (Button)findViewById(R.id.SaveButton);
        saveListener = new OnClickListener() {
            public void onClick(View v) {
            	Cigar cigar = new Cigar();
                olvidoRadioButton = (RadioButton)findViewById(R.id.olvidado);
        		Calendar c = Calendar.getInstance();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");                
                if (olvidoRadioButton.isChecked()){
    				TimePicker picker = (TimePicker)findViewById(R.id.TimePicker);                	
                	c.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
                	c.set(Calendar.MINUTE, picker.getCurrentMinute());
                }
            	cigar.setDate(sdf.format(c.getTime()));
            	cigar.setId((int)tipo.getSelectedItemId());
            	MyDBAdapter dba = MyDBAdapter.getInstance(getApplicationContext());
            	dba.insertEntry(cigar);
            	//showDialog(DIALOG_PAUSED_ID);
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
                String dbToSave = MyDBAdapter.getInstance(getApplicationContext()).getAllEntriesToSend();
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, dbToSave); 
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));            	
              }
          };
        sendButton.setOnClickListener(sendListener);          
        
    }
    
    static final int DIALOG_PAUSED_ID = 0;
    static final int DIALOG_GAMEOVER_ID = 1;
    
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = new Dialog(getApplicationContext());
        switch(id) {
        case DIALOG_PAUSED_ID:
            dialog.setTitle(R.string.success);// do the work to define the pause Dialog
            break;
        case DIALOG_GAMEOVER_ID:
            // do the work to define the game over Dialog
            break;
        default:
            dialog = null;
        }
        return dialog;
    }
}