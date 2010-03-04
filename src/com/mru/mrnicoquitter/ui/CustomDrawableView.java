package com.mru.mrnicoquitter.ui;

import com.mru.mrnicoquitter.R;

import android.R.style;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import android.widget.HorizontalScrollView;



	public class CustomDrawableView extends HorizontalScrollView{
	    private ShapeDrawable mDrawable;

	    public CustomDrawableView(Context context) {
	        super(context);

	        int x = 10;
	        int y = 10;
	        int width = 300;
	        int height = 50;
	        setHorizontalScrollBarEnabled(true);
	        mDrawable = new ShapeDrawable(new OvalShape());
	        mDrawable.getPaint().setColor(0xff74AC23);
	        mDrawable.setBounds(x, y, x + width, y + height);
			//setContentView(R.layout.main_scrollview_tablelayout);	
			setScrollContainer(true);
			
	        
	       
	    }

	    protected void onDraw(Canvas canvas) {
	        mDrawable.draw(canvas);
	        
	        Paint paint = new Paint();
	        paint.setStyle(Paint.Style.STROKE);
	        paint.setStrokeWidth(4);
	        paint.setColor(0xff74AC23);

	        
	        canvas.drawLine(0, 2, 500, 300, paint) ;
	        setHorizontalScrollBarEnabled(true);
	    }
	}

