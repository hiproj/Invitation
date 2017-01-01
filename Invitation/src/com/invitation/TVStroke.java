package com.invitation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.widget.TextView;

public class TVStroke extends TextView{
	
	int	strokeColor	= 0;
	
	public TVStroke(Context context){
		super(context);
	}
	
	public void setStrokeColor(int color){
		strokeColor = color;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		if (strokeColor == 0) {
			super.onDraw(canvas);
		} else {
			ColorStateList states = getTextColors();
			Paint paint = getPaint();
			// stroke
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(getTextSize() / 15);
			setTextColor(strokeColor);
			super.onDraw(canvas);
			// text
			paint.setStyle(Style.FILL);
			setTextColor(states);
			super.onDraw(canvas);
		}
	}
}
