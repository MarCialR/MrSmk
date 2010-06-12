package com.mru.mrnicoquitter;

import java.util.Iterator;

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
import com.mru.mrnicoquitter.beans.AnswerItem;
import com.mru.mrnicoquitter.beans.Encuesta;
import com.mru.mrnicoquitter.beans.EncuestaItem;
import com.mru.mrnicoquitter.beans.ui.EncuestaRadioButton;
import com.mru.mrnicoquitter.utils.UIUtils;
import com.mru.mrnicoquitter.xml.EncuestaXMLParser;

public class EncuestaActivity extends QActivity {

	private EncuestaXMLParser parser;
	Encuesta e; 
	Iterator<EncuestaItem> it;	
	EncuestaItem item;
    TextView questionText;
    TableRow answerRow;
    RadioGroup rg;
    Button goButton;
    TableLayout answersTL;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		String _encuesta = getIntent().getStringExtra(STR_EXTRA_ENCUESTA_NAME);

		//parser 	= new XMLParser("t_glover_nilsson",getApplicationContext()); 
		parser 	= new EncuestaXMLParser(_encuesta,getApplicationContext());		
		e 		= parser.parse();
        it 		= e.iterator();

        
        setContentView(R.layout.encuesta);	    
        questionText 	= (TextView)findViewById(R.id.Question);
        answersTL 		= (TableLayout)findViewById(R.id.AnswersTL);
        answersTL.setColumnShrinkable(0, true);        // IMPORTANTE		
        
		goButton 		= new Button(this);
		goButton.setText(getString(R.string.Btn_next));
		OnClickListener notificarListener = new OnClickListener() {
			public void onClick(View v) {

				int id = rg.getCheckedRadioButtonId();
				if (id == -1){
					UIUtils.showToastShort(getApplicationContext(), "U need to choose 1!!");
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
        	UIUtils.showToastLong(getApplicationContext(), "El resultado es :" + e.getResult() );
        	finish();
        }
		return;
	}


	@Override
	protected String[] getMandatoryFields() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected boolean isOKToLaunch() {
		// TODO Auto-generated method stub
		return false;
	}
}
