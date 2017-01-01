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
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.invitation.R;

public class FragInput extends Fragment {
	
	private ActMain actMain;
	private int cardSelIdx, cardResId;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		actMain = (ActMain) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){		
		return inflater.inflate(R.layout.messageinput, container, false);
	}
	
	public void setSelctCardIndexAndResId(int selIdx, int resId) {
		cardSelIdx = selIdx;
		cardResId = resId;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//
		ImageView iv = (ImageView) getView().findViewById(R.id.ivMessageInputBg);
		iv.setImageResource(cardResId);
		iv.setAlpha(180);
		//
		final EditText edt = (EditText) getView().findViewById(R.id.edtMessageInputText);
		edt.setText(
			"초청자 : 박상훈" + "\n" +
			"연락처 : " + getPhoneNumber() + "\n"
			);
		
		final TVStroke tvHidden = new TVStroke(actMain);
		FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		flp.gravity = Gravity.CENTER_HORIZONTAL;
		flp.setMargins(0, 50, 0, 0);
		tvHidden.setLayoutParams(flp);
		tvHidden.setGravity(Gravity.CENTER);
        tvHidden.setText("꼭!\n입사하고 싶습니다!");
        tvHidden.setTextColor(Color.parseColor("#61E1FE"));
        tvHidden.setTextSize(40);
        tvHidden.setStrokeColor(Color.BLUE);
        tvHidden.setTypeface(null, Typeface.BOLD);
        tvHidden.setVisibility(View.INVISIBLE);
        ((FrameLayout) getView().findViewById(R.id.inputFlRoot)).addView(tvHidden);
        
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
		
		getView().findViewById(R.id.ivShare).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				getView().findViewById(R.id.ivShare).setVisibility(View.INVISIBLE);
				edt.setCursorVisible(false);
				capture(getView().findViewById(R.id.inputFlRoot));
				edt.setCursorVisible(true);
				getView().findViewById(R.id.ivShare).setVisibility(View.VISIBLE);
				 
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
	}
	
	public String getPhoneNumber() {
		TelephonyManager mgr = (TelephonyManager) actMain.getSystemService(Context.TELEPHONY_SERVICE);
		return mgr.getLine1Number();
	}


}
