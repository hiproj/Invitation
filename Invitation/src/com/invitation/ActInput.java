package com.invitation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ActInput extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messageinput);
		int selIdx = getIntent().getIntExtra("selIdx", 0);
		ImageView iv = (ImageView) findViewById(R.id.ivMessageInputBg);
		iv.setImageResource(ActCardSelect.cardResId[selIdx]);
		iv.setAlpha(180);
		final EditText edt = (EditText) findViewById(R.id.edtMessageInputText);
		edt.setText(
			"초청자 : 박상훈" + "\n" +
			"연락처 : " + getPhoneNumber() + "\n"
			);
		
		final TextViewStroke tvHidden = new TextViewStroke(this);
		FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		flp.gravity = Gravity.CENTER_HORIZONTAL;
		flp.setMargins(0, 50, 0, 0);
		tvHidden.setLayoutParams(flp);
		tvHidden.setGravity(Gravity.CENTER);
        tvHidden.setText("꼭!\n입사하고 싶습니다!");
        tvHidden.setTextColor(Color.parseColor("#61E1FE"));
        tvHidden.setTextSize(40);
        tvHidden.setTypeface(null, Typeface.BOLD);
        tvHidden.setVisibility(View.INVISIBLE);
        ((FrameLayout)findViewById(R.id.inputFlRoot)).addView(tvHidden);
        
        new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				long durationMillis = 2500;
				AnimationSet aniSet = new AnimationSet(true);
				AlphaAnimation alphaAni = new AlphaAnimation(0, 1.0f);
				alphaAni.setDuration(durationMillis);
				aniSet.addAnimation(alphaAni);
				ScaleAnimation scaleAni = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
				scaleAni.setDuration(durationMillis);
				aniSet.addAnimation(scaleAni);
				aniSet.setFillAfter(true);
				tvHidden.startAnimation(aniSet);
				tvHidden.setVisibility(View.VISIBLE);
			}
		}, 1000);
		
		findViewById(R.id.ivShare).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				findViewById(R.id.ivShare).setVisibility(View.INVISIBLE);
				edt.setCursorVisible(false);
				capture(findViewById(R.id.inputFlRoot));
				edt.setCursorVisible(true);
				findViewById(R.id.ivShare).setVisibility(View.VISIBLE);
				 
				// 공유하기
				Uri uri = Uri.fromFile(new File(samplePath));
				Intent sharedIntent = new Intent(Intent.ACTION_SEND);
				sharedIntent.putExtra(Intent.EXTRA_STREAM, uri);
				sharedIntent.setType("image/*");
				startActivity(Intent.createChooser(sharedIntent, "전송하기"));
				
			}
		});
		
	}
	
	public static final String samplePath = Environment.getExternalStorageDirectory().toString()+"/sample.jpeg";
	
	public void capture(View container) {
		container.buildDrawingCache();
	    Bitmap captureView = container.getDrawingCache();
	    FileOutputStream fos;
	    try {
	        fos = new FileOutputStream(samplePath);
	        captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_LONG).show();
	}
	
	public String getPhoneNumber() {
		TelephonyManager mgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		return mgr.getLine1Number();
	}


}
