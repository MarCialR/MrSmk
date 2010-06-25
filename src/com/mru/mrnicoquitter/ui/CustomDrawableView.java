package com.mru.mrnicoquitter.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import android.widget.HorizontalScrollView;

public class CustomDrawableView extends HorizontalScrollView {
	private ShapeDrawable mDrawable;

	public CustomDrawableView(Context context) {
		super(context);
	}
	
}

