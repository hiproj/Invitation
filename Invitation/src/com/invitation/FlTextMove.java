package com.invitation;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FlTextMove extends FrameLayout{
	
	ArrayList<TVStroke>			tvList	= new ArrayList<TVStroke>();
	FrameLayout.LayoutParams	flpTarget;
	
	TVStroke tvTarget;
	
	public FlTextMove(Context context){
		super(context);
		init(context);
	}
	
	public FlTextMove(Context context, AttributeSet attr){
		super(context, attr);
		init(context);
	}
	
	public void init(Context context){
		TVStroke tv = new TVStroke(context);
		tv.setText("초대합니다.");
		tv.setTextSize(24);
		tv.setStrokeColor(Color.BLUE);
		tv.setTextColor(Color.CYAN);
		tv.setBackgroundColor(Color.argb(50, 0, 0, 150));
		
		tv.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.LEFT | Gravity.TOP));
		tv.setOnTouchListener(lst);
		tvList.add(tv);
		addView(tvList.get(0));
		
		tvTarget = tvList.get(0);
		flpTarget = (FrameLayout.LayoutParams) tvTarget.getLayoutParams();
	}
	
	OnTouchListener	lst	= new OnTouchListener(){
		
		float	downX, downY;
		int		downLeftMargin, downTopMargin;
		
		@Override
		public boolean onTouch(View v, MotionEvent event){
			tvTarget = (TVStroke) v;
			flpTarget = (FrameLayout.LayoutParams) tvTarget.getLayoutParams();
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					downX = event.getRawX();
					downY = event.getRawY();
					downLeftMargin = flpTarget.leftMargin;
					downTopMargin = flpTarget.topMargin;
				break;
				
				case MotionEvent.ACTION_MOVE:
					flpTarget.leftMargin = downLeftMargin + (int) (event.getRawX() - downX);
					flpTarget.topMargin = downTopMargin + (int) (event.getRawY() - downY);
					edgeOptimal();
					tvTarget.setLayoutParams(flpTarget);
				break;
				
				case MotionEvent.ACTION_UP:
				break;
			}
			return true;
		}
	};
	
	public void moveXY(int x, int y) {
		flpTarget.leftMargin += x;
		flpTarget.topMargin += y;
		edgeOptimal();
		tvTarget.setLayoutParams(flpTarget);
	}
	
	public void edgeOptimal() {
		int edgeX = getWidth() - tvTarget.getWidth();
		int edgeY = getHeight() - tvTarget.getHeight();
		
		if (flpTarget.leftMargin < 0)
			flpTarget.leftMargin = 0;
		else if (flpTarget.leftMargin > edgeX)
			flpTarget.leftMargin = edgeX;
		
		if (flpTarget.topMargin < 0)
			flpTarget.topMargin = 0;
		else if (flpTarget.topMargin > edgeY)
			flpTarget.topMargin = edgeY;
		
	}

	public void setTextColor(int color) {
		tvTarget.setTextColor(color);
	}

	public void setTextColorStroke(int color) {
		tvTarget.setStrokeColor(color);
	}

	public void setTextSize(int size) {
		tvTarget.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}
}
