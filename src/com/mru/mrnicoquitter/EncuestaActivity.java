package com.mru.mrnicoquitter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mru.mrnicoquitter.beans.Encuesta;
import com.mru.mrnicoquitter.beans.EncuestaItem;
import com.mru.mrnicoquitter.data.XMLParser;
import com.mru.mrnicoquitter.ui.AppUtils;

public class EncuestaActivity extends Activity {

	private XMLParser parser;
	private int encuestaItemCounter;
	
	ArrayList<EncuestaItem> items;	
	EncuestaItem it;
    TableLayout table;
    TableRow questionRow;
    TextView questionText;
    TableRow answerRow;
    RadioGroup rg;
    Button goButton;

    @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		encuestaItemCounter = 0;
		
		AppUtils.showDebug(getApplicationContext(), "Encuesta - onCreate!!");
		
		parser = new XMLParser("fagerstrom",getApplicationContext()); 

		Encuesta e = parser.parse();
		

		items = e.getItems();

        table 		= new TableLayout(this);
        // Es necesario?
        table.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        // IMPORTANTE
        table.setColumnShrinkable(0, true);		

		goButton = new Button(this);
		goButton.setText("Siguiente");
		OnClickListener notificarListener = new OnClickListener() {
			public void onClick(View v) {
				@SuppressWarnings("unused")
				int id = rg.getCheckedRadioButtonId();
				RadioButton r = (RadioButton)findViewById(id);

				encuestaItemCounter++;
				showNextQuestion();
			}
		};
		goButton.setOnClickListener(notificarListener);
		
        showNextQuestion();
		
        setContentView(table);		
	}

	
	private void showNextQuestion(){
		table.removeAllViews();

        if (encuestaItemCounter != items.size()){
        	RadioButton rb;
        	it 			= items.get(encuestaItemCounter);
        	
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
        	finish();
        }
		return;
	}
}
