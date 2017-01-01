package com.invitation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.invitation.R;


public class FragSplash extends Fragment {
	ItfEvent event;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		event = (ItfEvent) activity;
		// 차후 로딩 종료 처리
		new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
			@Override
			public void run(){
				event.splashEnd();
			}
		}, 1000);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.splash, container, false);
	};
	
	
}
