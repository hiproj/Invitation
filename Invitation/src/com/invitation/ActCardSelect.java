package com.invitation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class ActCardSelect extends Activity {
	
	static int[] cardResId = {
		R.drawable.val0,
		R.drawable.val1,
		R.drawable.val2,
		R.drawable.val3,
	};
	
	ImageView[] iv = new ImageView[cardResId.length];
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardselect);
		LinearLayout ctLTop = (LinearLayout) findViewById(R.id.containerLeftTop);
		LinearLayout ctRTop = (LinearLayout) findViewById(R.id.containerRightTop);
		LinearLayout ctLBottom = (LinearLayout) findViewById(R.id.containerLeftBottom);
		LinearLayout ctRBottom = (LinearLayout) findViewById(R.id.containerRightBottom);
		
		for (int i = 0; i < cardResId.length; i++) {
			iv[i] = new ImageView(this);
			final int idx = i;
			iv[i].setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(ActCardSelect.this, ActInput.class);
					intent.putExtra("selIdx", idx);
					startActivity(intent);
				}
			});
		}
		
		iv[0].setImageResource(cardResId[0]);
		ctLTop.addView(iv[0]);
		
		iv[1].setImageResource(cardResId[1]);
		ctRTop.addView(iv[1]);
		
		iv[2].setImageResource(cardResId[2]);
		ctLBottom.addView(iv[2]);

		iv[3].setImageResource(cardResId[3]);
		ctRBottom.addView(iv[3]);
		
		
	}
}
