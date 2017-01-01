package com.invitation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;

public class DlgTextSet extends Dialog{
	
	ItfEvent	itfEvent;
	Context		context;
	TextView	tv;
	
	public DlgTextSet(Context context, ItfEvent itfEvent, TextView tv){
		super(context);
		this.itfEvent = itfEvent;
		this.context = context;
		this.tv = tv;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlg_text_set);
		setSize(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		final EditText edtTextSet = (EditText) findViewById(R.id.edtTextSet);
		edtTextSet.setText(tv.getText().toString());
		findViewById(R.id.btnTextSetOk).setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v){
				String input = edtTextSet.getText().toString();
				tv.setText(input);
				dismiss();
			}
		});
	}
	
	public void setSize(int width, int height){
		LayoutParams lp = getWindow().getAttributes();
		lp.width = width;
		lp.height = height;
		getWindow().setAttributes(lp);
	}
}
