package com.invitation;

import java.lang.reflect.Field;
import java.util.Arrays;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DlgColorPick extends Dialog{
	
	ItfEvent itfEvent;
	
	public DlgColorPick(Context context, ItfEvent itfEvent){
		super(context);
		this.itfEvent = itfEvent;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlg_color_pick);
		setSize(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout ll = (LinearLayout) findViewById(R.id.llDlgColorPickArea);
		ColorOnClick lst = new ColorOnClick();
		for (int i = 0; i < ll.getChildCount(); i++) {
			LinearLayout childRow = (LinearLayout) ll.getChildAt(i);
			for (int j = 0; j < childRow.getChildCount(); j++) {
				ImageView iv = (ImageView) childRow.getChildAt(j);
				iv.setOnClickListener(lst);
			}
		}
	}
	
	class ColorOnClick implements android.view.View.OnClickListener{
		
		@Override
		public void onClick(View v){
			int color = getBackgroundColor(v);
			itfEvent.setColorChange(color);
			dismiss();
		}
		
		public int getBackgroundColor(View view){
			Drawable drawable = view.getBackground();
			if (drawable instanceof ColorDrawable) {
				ColorDrawable colorDrawable = (ColorDrawable) drawable;
				try {
					Field field = colorDrawable.getClass().getDeclaredField("mState");
					field.setAccessible(true);
					Object object = field.get(colorDrawable);
					field = object.getClass().getDeclaredField("mUseColor");
					field.setAccessible(true);
					return field.getInt(object);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return 0;
		}
	}
	
	public void setSize(int width, int height){
		LayoutParams lp = getWindow().getAttributes();
		lp.width = width;
		lp.height = height;
		getWindow().setAttributes(lp);
	}
}
