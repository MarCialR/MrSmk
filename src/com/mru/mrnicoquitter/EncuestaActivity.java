package com.mru.mrnicoquitter;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mru.mrnicoquitter.beans.Encuesta;
import com.mru.mrnicoquitter.beans.EncuestaItem;
import com.mru.mrnicoquitter.data.EncuestaHandler;
import com.mru.mrnicoquitter.data.XMLParser;
import com.mru.mrnicoquitter.ui.AppUtils;

public class EncuestaActivity extends Activity {

	private XMLParser mParser;
	private int encuestaItemCounter;
	
	ArrayList<EncuestaItem> items;	
	EncuestaItem it;
    TableLayout table;
    TableRow questionRow;
    TextView questionText;
    TableRow answerRow;
    RadioGroup rg;
    
    CheckBox check1 ;
    TextView text1;	
    
    Button goButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		AppUtils.showDebug(getApplicationContext(), "Encuesta - onCreate!!");

		mParser = new XMLParser();
		
		InputStream is = getApplicationContext().getResources().openRawResource(R.raw.cds);
		EncuestaHandler myHandler = new EncuestaHandler();
		mParser.parse(is, myHandler);
		Encuesta e = myHandler.getEncuesta();
		encuestaItemCounter = 0;

		items = e.getItems();

        table 		= new TableLayout(this);

		goButton = new Button(this);
		goButton.setText("Siguiente");
		OnClickListener notificarListener = new OnClickListener() {
			public void onClick(View v) {
				encuestaItemCounter++;
				nextQuestion();
			}
		};
		goButton.setOnClickListener(notificarListener);
		
        nextQuestion();
		
        setContentView(table);		
	}
	
	private void nextQuestion(){
		table.removeAllViews();

        if (encuestaItemCounter != items.size()){
        	it 			= items.get(encuestaItemCounter);
        	RadioButton rb;
        	
            questionRow 	= new TableRow(this);
            questionText= new TextView(this);
            questionText.setText("\n"+it.getQues()+"\n");
            questionText.setTextSize(24f);
            //questionText.setTextColor(R.color.green_dark);
            questionText.setGravity(Gravity.CENTER_HORIZONTAL);            
            questionRow.addView(questionText);
            table.addView(questionRow);

    		answerRow = new TableRow(this);
    		rg = new RadioGroup(this);
    		int countermini = 0;
        	for (String an:it.getAnsws()){
        		rb = new RadioButton(this);
        		rb.setText(an);
        		rg.addView(rb,countermini++);
        	}
        	answerRow.addView(rg);
        	table.addView(answerRow);
        	table.addView(new TextView(this));
    		table.addView(goButton);
        }else {
        	System.out.println("SEACABOOOOOOO");
        }
		return;
	}
}
