package com.mru.mrnicoquitter;

import com.mru.mrnicoquitter.R;

import android.content.Context; 
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.graphics.Canvas; 
import android.graphics.Paint; 
import android.os.Bundle; 
import android.view.Display; 
import android.view.GestureDetector; 
import android.view.MotionEvent; 
import android.view.View; 
import android.view.WindowManager; 
import android.view.GestureDetector.OnGestureListener; 
import android.view.ViewGroup.LayoutParams; 

public class TimelineActivity extends QActivity { 
    
   ScrollableImageView scrollImageView; 
    
   @Override 
   public void onCreate(Bundle savedInstanceState) { 
      super.onCreate(savedInstanceState); 
      
      WindowManager w = getWindowManager(); 
      Display d = w.getDefaultDisplay(); 
      
      scrollImageView = new ScrollableImageView(this, 
            BitmapFactory.decodeResource(getResources(), R.drawable.big_1024x768), 
            d.getWidth(), d.getHeight(), 
            null); 
      setContentView(scrollImageView); 
   } 
    
   public boolean onTouchEvent(MotionEvent event) { 
      return scrollImageView.getGestureScanner().onTouchEvent(event); 
   } 

   class ScrollableImageView extends View { 
      
      int scrollRate = 40; 
      int scrollX = 0; 
      int scrollY = 0; 
      boolean scrollHorizontalEnabled = true; 
      boolean scrollVerticalEnabled = true; 
      //// 
      Bitmap image; 
      Bitmap bufferImage; 
      int maxWidth; 
      int maxHeight; 
      int pictureWidth; 
      int pictureHeight; 
      //// 
      Paint paint; 
      GestureDetector gestureScanner; 

      public ScrollableImageView(Context context, Bitmap image, int width, 
            int height, Paint paint) { 
         super(context); 
         this.image = image; 
         this.paint = paint; 
          
         bufferImage = Bitmap.createBitmap(image); 
          
         calculateSize(width, height); 
         createGestureListener(); 
      } 
      
      public ScrollableImageView(Context context, Bitmap image, 
            int width, int height, Paint paint, 
            boolean scrollHorizontal, boolean scrollVertical) { 
         super(context); 
         this.image = image; 
         this.paint = paint; 
         this.scrollHorizontalEnabled = scrollHorizontal; 
         this.scrollVerticalEnabled = scrollVertical; 
          
         bufferImage = Bitmap.createBitmap(image); 
          
         calculateSize(width, height); 
         createGestureListener(); 
      } 
      
      protected void calculateSize(int width, int height) { 
          
         //picture size 
         pictureWidth = image.getWidth(); 
         pictureHeight = image.getHeight(); 
          
         //window size 
         maxWidth = Math.min(pictureWidth, width); 
         maxHeight = Math.min(pictureHeight, height); 
          
         //layout size 
         setLayoutParams(new LayoutParams(pictureWidth, pictureHeight)); 
      } 
      
      protected void createGestureListener(){ 
         setGestureScanner(new GestureDetector(new OnGestureListener() { 
            
            public boolean onScroll(MotionEvent event1, MotionEvent event2, 
                  float distanceX, float distanceY) { 
               handleScroll(distanceX, distanceY); 
               return true; 
            } 
            public boolean onDown(MotionEvent event) { 
               return true; 
            } 
            public boolean onFling(MotionEvent event1, MotionEvent event2, 
                  float velocityX, float velocityY) { 
               return true; 
            } 
            public void onLongPress(MotionEvent event) { 
               //do nothing 
            } 
            public void onShowPress(MotionEvent event) { 
               //do nothing 
            } 
            public boolean onSingleTapUp(MotionEvent event) { 
               return true; 
            } 
         })); 
      } 

      @Override 
      protected void onDraw(Canvas canvas) { 
         canvas.drawBitmap(bufferImage, 0, 0, paint); 
      } 

      protected void handleScroll(float distX, float distY) { 
          
         int maxScrollX = Math.max(pictureWidth - maxWidth, 0); 
         int maxScrollY = Math.max(pictureHeight - maxHeight, 0); 
          
         //X-Axis 
         if(scrollHorizontalEnabled){ 
            if (distX > 6.0) { 
               if (scrollX < maxScrollX - scrollRate) { 
                  scrollX += scrollRate; 
               } 
               else { 
                  scrollX = maxScrollX; 
               } 
            } else if (distX < -6.0) { 
               if (scrollX >= scrollRate) { 
                  scrollX -= scrollRate; 
               } 
               else { 
                  scrollX = 0; 
               } 
            } 
         } 

         //Y-AXIS 
         if(scrollVerticalEnabled){ 
            if (distY > 6.0) { 
               if (scrollY < maxScrollY - scrollRate) { 
                  scrollY += scrollRate; 
               } 
               else { 
                  
               } 
            } else if (distY < -6.0) { 
               if (scrollY >= scrollRate) { 
                  scrollY -= scrollRate; 
               } 
               else { 
                  scrollY = 0; 
               } 
            } 
         } 
          
         //Swap image 
         if ((scrollX <= maxWidth) && (scrollY <= maxHeight)) { 
            swapImage(); 
            invalidate(); 
         } 
      } 
      
      protected void swapImage() { 
         bufferImage = Bitmap.createBitmap(image, scrollX, scrollY, 
               maxWidth, maxHeight); 
      } 

      public GestureDetector getGestureScanner() { 
         return gestureScanner; 
      } 

      public void setGestureScanner(GestureDetector gestureScanner) { 
         this.gestureScanner = gestureScanner; 
      } 
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