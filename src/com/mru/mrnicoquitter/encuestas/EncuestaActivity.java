package com.mru.mrnicoquitter.encuestas;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;
import com.mru.mrnicoquitter.utils.Utils;

public class EncuestaActivity extends Activity {

	private XMLParser parser;

	Encuesta e; 
	Iterator<EncuestaItem> it;	
	EncuestaItem item;

    TextView questionText;
    TableRow answerRow;
    RadioGroup rg;
    Button goButton;
    TableLayout answersTL;

    @Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        AppUtils.showDebug(getApplicationContext(), "Encuesta - onCreate!!");
        
        setContentView(R.layout.encuesta);	        
        
        answersTL = (TableLayout)findViewById(R.id.AnswersTL);
        // IMPORTANTE
        answersTL.setColumnShrinkable(0, true);		

        questionText = (TextView)findViewById(R.id.Question);        
        
		parser 	= new XMLParser("t_glover_nilsson",getApplicationContext()); 
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
		
        	
	}

	
	private void showNextQuestion(){
		answersTL.removeAllViews();

        if (it.hasNext()){
        	EncuestaRadioButton rb;
        	item 				= it.next();
        	
            questionText.setText("\n"+item.getQues()+"\n");
            questionText.setTextSize(20f);
            //questionText.setTextColor(R.color.green_dark);
            questionText.setGravity(Gravity.CENTER_HORIZONTAL);           
            
            answerRow = new TableRow(this);
    		rg = new RadioGroup(this);
        	for (AnswerItem an:item.getAnsws()){
        		rb = new EncuestaRadioButton(this);
        		rb.setText(an.getText());
        		rb.setValue(an.getValue());
         		rg.addView(rb);
        	}
        	answerRow.addView(rg);
        	answersTL.addView(answerRow);
        	answersTL.addView(new TextView(this));
        	answersTL.addView(goButton);
        }else {
        	AppUtils.showToastLong(getApplicationContext(), "El resultado es :" + e.getResult() );
        	finish();
        }
		return;
	}
}
