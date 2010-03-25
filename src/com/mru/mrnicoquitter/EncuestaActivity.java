package com.mru.mrnicoquitter;

import java.util.ArrayList;
import java.util.Iterator;

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

import com.mru.mrnicoquitter.encuestas.AnswerItem;
import com.mru.mrnicoquitter.encuestas.Encuesta;
import com.mru.mrnicoquitter.encuestas.EncuestaItem;
import com.mru.mrnicoquitter.encuestas.EncuestaRadioButton;
import com.mru.mrnicoquitter.encuestas.XMLParser;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.utils.Utils;

public class EncuestaActivity extends Activity {

	private XMLParser parser;

	Encuesta e; 
	Iterator<EncuestaItem> it;	
	EncuestaItem item;
    TableLayout table;
    TableRow questionRow;
    TextView questionText;
    TableRow answerRow;
    RadioGroup rg;
    Button goButton;

    @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        table 				= new TableLayout(this);
        // Es necesario?
        table.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        // IMPORTANTE
        table.setColumnShrinkable(0, true);		
        AppUtils.showDebug(getApplicationContext(), "Encuesta - onCreate!!");

		parser 	= new XMLParser("fagerstrom",getApplicationContext()); 
		e 		= parser.parse();
        it 		= e.iterator();

		goButton 			= new Button(this);
		goButton.setText("Siguiente");
		OnClickListener notificarListener = new OnClickListener() {
			public void onClick(View v) {
				@SuppressWarnings("unused")
				int id = rg.getCheckedRadioButtonId();
				if (id == -1){
					AppUtils.showToastShort(getApplicationContext(), "U need to choose 1!!");
				}else{
					EncuestaRadioButton r = (EncuestaRadioButton)findViewById(id);
					e.sumaResultado(r.getValue());
					showNextQuestion();
				}
			}
		};
		goButton.setOnClickListener(notificarListener);
		
        showNextQuestion();
		
        setContentView(table);		
	}

	
	private void showNextQuestion(){
		table.removeAllViews();

        if (it.hasNext()){
        	EncuestaRadioButton rb;
        	item 				= it.next();
        	
            questionRow 	= new TableRow(this);
            questionText	= new TextView(this);
            questionText.setText("\n"+item.getQues()+"\n");
            questionText.setTextSize(24f);
            //questionText.setTextColor(R.color.green_dark);
            questionText.setGravity(Gravity.CENTER_HORIZONTAL);            
            questionRow.addView(questionText);
            table.addView(questionRow);

            answerRow = new TableRow(this);
    		rg = new RadioGroup(this);
        	for (AnswerItem an:item.getAnsws()){
        		rb = new EncuestaRadioButton(this);
        		rb.setText(an.getText());
        		rb.setValue(an.getValue());
         		rg.addView(rb);
        	}
        	answerRow.addView(rg);
        	table.addView(answerRow);
        	table.addView(new TextView(this));
    		table.addView(goButton);
        }else {
        	AppUtils.showToastLong(getApplicationContext(), "El resultado es :" + e.getResult() );
        	finish();
        }
		return;
	}
}
