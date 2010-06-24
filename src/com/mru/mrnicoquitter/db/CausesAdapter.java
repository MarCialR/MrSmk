package com.mru.mrnicoquitter.db;

import com.mru.mrnicoquitter.R;
import com.mru.mrnicoquitter.cigars.DayManagerSGTon;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CausesAdapter extends BaseAdapter { 
	
    private String[] arrayCauses;
    private Context mContext;
    
	public CausesAdapter(Context context) { 
         this.mContext = context; 
         String[] tmpArray = context.getResources().getStringArray(R.array.cigars);
         if (!DayManagerSGTon.getInstance().canBeNewDay()){
        	 this.arrayCauses = new String[tmpArray.length-1];
        	 System.arraycopy(tmpArray, 1, this.arrayCauses, 0, tmpArray.length-1);
         }
         else{
        	 this.arrayCauses = new String[tmpArray.length];
        	 System.arraycopy(tmpArray, 0, this.arrayCauses, 0, tmpArray.length);
         }
    }
    public int getCount() { 
         return arrayCauses.length; 
    } 
    public Object getItem(int position) { 
         return arrayCauses[position]; 
    } 
    public long getItemId(int position) { 
         return position; 
    } 

    public View getView(int position, View convertView, ViewGroup parent) { 
        CausesVW sv; 
        if (convertView == null) { 
             sv = new CausesVW(mContext, arrayCauses[position]); 
        } else { 
             sv = (CausesVW) convertView; 
             sv.setText(arrayCauses[position]); 
        } 
        return sv; 
   } 
    private class CausesVW extends LinearLayout { 
        private TextView causesTextVw; 
        public CausesVW(Context context, String str) { 
             super(context); 
             this.setOrientation(VERTICAL); 
             causesTextVw = new TextView(context); 
             causesTextVw.setHeight(30); 
             causesTextVw.setText(str); 
             causesTextVw.setTextColor(Color.BLUE);
             causesTextVw.setTextSize(14);
             causesTextVw.setGravity(HORIZONTAL);
             LayoutParams ll =new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

             addView(causesTextVw, ll); 
        } 
        public void setText(String title) { 
             causesTextVw.setText(title); 
        } 
   } 
} 