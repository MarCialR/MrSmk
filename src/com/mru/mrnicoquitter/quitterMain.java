package com.mru.mrnicoquitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class quitterMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner tipo = (Spinner)this.findViewById(R.id.TypeSpinner);
        Map a1 = new HashMap();
        a1.put("text", "Yolovalgo");
        Map a2 = new HashMap();
        a1.put("text", "Amigos");
        List l = new Vector();
        l.add(a1);
        l.add(a2);
        String []s= {"text"};
        int []i= {1};
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        SpinnerAdapter sa = new SimpleAdapter(getApplicationContext(), l, 1,s,i);
        tipo.setAdapter(adapter);
        setContentView(R.layout.main);        
        OnClickListener okButtonListener = new OnClickListener(){ 
            // @Override 
            public void onClick(DialogInterface arg0, int arg1) { 

            } 
       }; 
       OnClickListener cancelButtonListener = new OnClickListener(){ 
            // @Override 
            public void onClick(DialogInterface arg0, int arg1) { 
                 // Do nothing 
            } 
       };         
        new AlertDialog.Builder(this) 
        .setTitle("Question") 
        .setMessage("Is this the one you whant to add?") 
        .setPositiveButton("OK", okButtonListener) 
        .setNegativeButton("Cancel", cancelButtonListener) 
        .show();
    }
    
}