package com.mru.mrnicoquitter.ui;

import com.mru.mrnicoquitter.R;

import android.R.style;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
<<<<<<< HEAD:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java
=======
import android.view.View;
>>>>>>> origin/dev:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java
import android.widget.HorizontalScrollView;

public class CustomDrawableView extends HorizontalScrollView {
	private ShapeDrawable mDrawable;

	public CustomDrawableView(Context context) {
		super(context);

<<<<<<< HEAD:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java
		int x = 10;
		int y = 10;
		int width = 300;
		int height = 50;
		setHorizontalScrollBarEnabled(true);
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		mDrawable.setBounds(x, y, x + width, y + height);
		// setContentView(R.layout.main_scrollview_tablelayout);
		super.setScrollContainer(true);
	}
=======
	public class CustomDrawableView extends HorizontalScrollView{
	    private ShapeDrawable mDrawable;
>>>>>>> origin/dev:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java

	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);

<<<<<<< HEAD:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		paint.setColor(0xff74AC23);

		canvas.drawLine(0, 2, 500, 300, paint);
		setHorizontalScrollBarEnabled(true);
=======
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
>>>>>>> origin/dev:src/com/mru/mrnicoquitter/ui/CustomDrawableView.java
	}
	
	 /*
	 public void handleScroll(float distX, float distY) 
	 { 
	      // X-Axis //////////////////////////////// 
	       
	      if(distX > 6.0) 
	      { 
	           if(scrollX < 460) 
	           { 
	                scrollX += 15; 
	           } 
	      } 
	      else if(distX < -6.0) 
	      { 
	           if(scrollX >= 15) 
	           { 
	                scrollX -= 15; 
	           } 
	      } 
	      //////////////////////////////////////////// 
	            
	      // Y-AXIS ////////////////////////////////// 
	      if(distY > 6.0) 
	      { 
	           if(scrollY < 100) 
	           { 
	                scrollY += 15; 
	           } 
	      } 
	      else if(distY < -6.0) 
	      { 
	           if(scrollY >= 15) 
	           { 
	                scrollY -= 15; 
	           } 
	      }               
	      //////////////////////////////////////////// 
	       
	      if((scrollX <= 480) && (scrollY <= 120)) 
	      { 
	           adapt = Bitmap.createBitmap(bmp, scrollX, scrollY, 320, 480);               
	           invalidate(); 
	      } 
	 } 	*/
	
}

