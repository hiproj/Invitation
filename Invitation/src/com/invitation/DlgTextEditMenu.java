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

public class DlgTextEditMenu extends Dialog{
	
	ItfEvent	itfEvent;
	int[]		styleIdList	= { 
		R.id.tvSetTextSize, 
		R.id.tvSetTextColor, 
		R.id.tvSetTextColorStroke, 
		R.id.tvSetText,
							};
	
	public DlgTextEditMenu(Context context, ItfEvent itfEvent){
		super(context);
		this.itfEvent = itfEvent;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlg_text_edit_menu);
		setSize(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		StyleSelect lst = new StyleSelect();
		for (int styleId : styleIdList)
			findViewById(styleId).setOnClickListener(lst);
	}
	
	class StyleSelect implements android.view.View.OnClickListener{
		
		@Override
		public void onClick(View v){
			itfEvent.selectTextStyle(v.getId());
			dismiss();
		}
	}
	
	public void setSize(int width, int height){
		LayoutParams lp = getWindow().getAttributes();
		lp.width = width;
		lp.height = height;
		getWindow().setAttributes(lp);
	}
}
