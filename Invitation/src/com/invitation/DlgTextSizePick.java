package com.invitation;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DlgTextSizePick extends Dialog{
	
	ItfEvent	itfEvent;
	Context		context;
	TextView	tv;
	int			min	= 12;
	int			max	= 99;
	
	public DlgTextSizePick(Context context, ItfEvent itfEvent, TextView tv){
		super(context);
		this.itfEvent = itfEvent;
		this.context = context;
		this.tv = tv;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dlg_text_size_pick);
		setSize(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ListView lv = (ListView) findViewById(R.id.lvTextSizeList);
		ArrayList<String> sizeList = new ArrayList<String>();
		for (int i = min; i <= max; i++)
			sizeList.add(Integer.toString(i));
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simple_list_item_1, sizeList);
		lv.setAdapter(adapter);
		lv.setSelection((int)tv.getTextSize() - min);
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				Log.d("d", "textSize : " + adapter.getItem(position));
				itfEvent.setTextSize(Integer.parseInt(adapter.getItem(position)));
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
