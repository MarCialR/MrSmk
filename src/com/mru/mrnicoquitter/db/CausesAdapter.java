package com.mru.mrnicoquitter.db;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CausesAdapter extends BaseAdapter { 

	
	
	
    private String[] content;
    private Context mContext;
    
    public CausesAdapter(Context context, String[] content) { 
         this.mContext = context; 
         this.content = content;
    } 
    public int getCount() { 
         return content.length; 
    } 
    public Object getItem(int position) { 
         return content[position]; 
    } 
    public long getItemId(int position) { 
         return position; 
    } 

    public View getView(int position, View convertView, ViewGroup parent) { 
        MyView sv; 
        if (convertView == null) { 
             sv = new MyView(mContext, content[position]); 
        } else { 
             sv = (MyView) convertView; 
             sv.setText(content[position]); 
        } 
        return sv; 
   } 
    private class MyView extends LinearLayout { 
        private TextView text; 
        public MyView(Context context, String str) { 
             super(context); 
             this.setOrientation(VERTICAL); 
             text = new TextView(context); 
             text.setHeight(20); 
             text.setText(str); 
             text.setTextColor(Color.BLACK);
             text.setTextSize(14);
             text.setGravity(HORIZONTAL);
             addView(text, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)); 
        } 
        public void setText(String title) { 
             text.setText(title); 
        } 
   } 
} 