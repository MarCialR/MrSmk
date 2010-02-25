package com.mru.mrnicoquitter.timer;

import com.mru.mrnicoquitter.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;


	public class Chronom extends Activity implements Runnable {
		 
        private String pi_string;
        private TextView tv;
        private ProgressDialog pd;
 
        @Override
        public void onCreate(Bundle icicle) {
                super.onCreate(icicle);
                setContentView(R.layout.main);
 /*
                tv = (TextView) this.findViewById(R.id.main);
                tv.setText("Press any key to start calculation");
   */     }
 
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
 
                pd = ProgressDialog.show(this, "Working..", "Calculating Pi", true,
                                false);
 
                Thread thread = new Thread(this);
                thread.start();
 
                return super.onKeyDown(keyCode, event);
        }
 
        public void run() {
                pi_string = "";//Math.Pi.computePi(800).toString();
                handler.sendEmptyMessage(0);
        }
 
        private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                        pd.dismiss();
                        tv.setText(pi_string);
 
                }
        };
        
/*
        public class Coche extends Thread implements Comparable<Coche>{


        	private List<MovCocheListener> movCocheListeners = new ArrayList<MovCocheListener>(); 
        	
        	public void addMovCocheListener(MovCocheListener _l){
        		movCocheListeners.add(_l);
        	}
        	
        	public void removeCocheListener(MovCocheListener _l){
        		movCocheListeners.remove(_l);
        	}
        	
        	private void notifyListeners(){
        		MovCocheEvent event = new MovCocheEvent(this,1,"");
        		Iterator<MovCocheListener> it = movCocheListeners.iterator();	
        		while (it.hasNext()){
        			it.next().onCocheMov(event);
        		}
        	}
        	
        	@Override
        	public void run() {
        		int inicialX = posX;
        		int inicialY = posY;
        		
        		while (true) {
        			setPosX(inicialX);
        			setPosY(inicialY);
        			for (int i = 1; i < 500; i++) {
        				setPosX(getPosX() + 1);
        				yield();
        			}
        			for (int i = 1; i < 100; i++) {
        				setPosY(getPosY() + 1);
        				yield();
        				try {
        					this.sleep(5);
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			}
        			for (int i = 1; i < 500; i++) {
        				setPosX(getPosX() - 1);
        				yield();
        			}
        			for (int i = 1; i < 100; i++) {
        				setPosY(getPosY() - 1);
        				yield();
        				try {
        					this.sleep(5);
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			}
        		}	
        		
        	}
        	
        	Coche(String _matricula, Coches _padre, int _x,int _y) throws MatriculaException{
        		setMatricula(_matricula);
        		setPCoches(_padre);
        		setPosX(_x);
        		setPosY(_y);
        	}
        	

        	public Coche(String _matricula, String _modelo, String _color, Coches _padre) throws MatriculaException {
        		this(_matricula,_padre,0,0);
        		this.color = _color;
        		this.modelo = _modelo;
        	}
        	public Coche (String _matricula) throws MatriculaException{
        		setMatricula(_matricula);
        	}


        	public String getMatricula() {
        		return matricula;
        	}


        	

        	public static void main(String[] args) {
        		try {
        			Coche c = new Coche("   Ctr-2345-edr   ");
        		} catch (MatriculaException e) {
        			// TODO Auto-generated catch block
        			System.out.println(e.getType());
        		}
        	}
        }
*/
 
}