package com.invitation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.widget.TextView;

public class TextViewStroke extends TextView{
	
	public TextViewStroke(Context context){
		super(context);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		ColorStateList states = getTextColors();
		Paint paint = getPaint();
		// stroke
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(getTextSize() / 15);
		setTextColor(Color.BLUE);
		super.onDraw(canvas);
		// text
		paint.setStyle(Style.FILL);
		setTextColor(states);
		super.onDraw(canvas);
	}
}
