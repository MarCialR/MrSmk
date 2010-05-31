package com.mru.mrnicoquitter.encuestas;

import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static com.mru.mrnicoquitter.Global.*;
import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.ui.AppUtils;

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

		String _encuesta = getIntent().getStringExtra("encuesta");
        
        
        answersTL = (TableLayout)findViewById(R.id.AnswersTL);
        // IMPORTANTE
        answersTL.setColumnShrinkable(0, true);		

        questionText = (TextView)findViewById(R.id.Question);        
        
		//parser 	= new XMLParser("t_glover_nilsson",getApplicationContext()); 
		parser 	= new XMLParser(_encuesta,getApplicationContext());		
		e 		= parser.parse();
        it 		= e.iterator();

        

        
        
		goButton 			= new Button(this);
		goButton.setText(getString(R.string.Btn_next));
		OnClickListener notificarListener = new OnClickListener() {
			public void onClick(View v) {

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
        	
            questionText.setText(NEWLINE+item.getQues()+NEWLINE);
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
