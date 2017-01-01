package com.invitation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class FragMoveTest extends Fragment{
	
	ActMain actMain;
	FlTextMove	tm;
	ImageView	ivControler;
	EditText	edt;
	ItfEvent	itfEvent;
	
	private int cardSelIdx, cardResId;
	
	public static final String samplePath = Environment.getExternalStorageDirectory().toString()+"/sample.jpeg";
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		actMain = (ActMain) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.movetest, container, false);
	}
	
	public void setSelctCardIndexAndResId(int selIdx, int resId) {
		cardSelIdx = selIdx;
		cardResId = resId;
	}
	
	public void setBackground(int resId) {
		ImageView iv = (ImageView) getView().findViewById(R.id.ivBgTextMove);
		iv.setImageResource(resId);
		iv.setAlpha(180);
		
		Log.d("d", "val0 : " + R.drawable.val0);
		Log.d("d", "cardResId : " + cardResId);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		itfEvent = (ActMain) getActivity();
		
		setBackground(cardResId);
		
		tm = (FlTextMove) getView().findViewById(R.id.textMove);
		ivControler = (ImageView) getView().findViewById(R.id.ivTextMoveControl);
		ivControler.setOnTouchListener(new OnTouchListener(){
			
			float	downX, downY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event){
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						downX = event.getRawX();
						downY = event.getRawY();
						v.setBackgroundColor(Color.parseColor("#88ff88"));
					break;
					case MotionEvent.ACTION_MOVE:
						float moveX = event.getRawX();
						float moveY = event.getRawY();
						tm.moveXY((int) (moveX - downX), (int) (moveY - downY));
						downX = moveX;
						downY = moveY;
					break;
					case MotionEvent.ACTION_UP:
						v.setBackgroundColor(0);
					break;
				}
				return true;
			}
		});
		getView().findViewById(R.id.ivSetTextStyle).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				itfEvent.startStyleChange();
			}
		});
		
		getView().findViewById(R.id.ivShare).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				View capLayout = getView().findViewById(R.id.flTextMoveCaptureLayout);
				View capArea = getView().findViewById(R.id.ivBgTextMove);
				capture(capLayout, capArea);
				//
				Uri uri = Uri.fromFile(new File(samplePath));
				Intent sharedIntent = new Intent(Intent.ACTION_SEND);
				sharedIntent.putExtra(Intent.EXTRA_STREAM, uri);
				sharedIntent.setType("image/*");
				startActivity(Intent.createChooser(sharedIntent, "전송하기"));
			}
		});
	}
	
	public void setTextColor(int color){
		tm.setTextColor(color);
	}

	public void setTextColorStroke(int color) {
		tm.setTextColorStroke(color);
	}

	public void setTextSize(int size) {
		tm.setTextSize(size);
	}

	public FlTextMove getFlTextMove() {
		return tm;
	}

	public void capture(View container, View area) {
		container.buildDrawingCache();
	    Bitmap captureView = container.getDrawingCache();
	    captureView = Bitmap.createBitmap(captureView, area.getLeft(), area.getTop(), area.getWidth(), area.getHeight());
	    FileOutputStream fos;
	    try {
	        fos = new FileOutputStream(samplePath);
	        captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
