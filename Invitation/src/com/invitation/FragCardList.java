package com.invitation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.invitation.R;


public class FragCardList extends Fragment {
	
	int[] cardSelectAreaIdList = {
		R.id.containerLeftTop,
		R.id.containerRightTop,
		R.id.containerLeftBottom,
		R.id.containerRightBottom,
	};
	
	int[] cardDrawableIdList = {
		R.drawable.val0,
		R.drawable.val1,
		R.drawable.val2,
		R.drawable.val3,
	};
	
	ImageView[] ivCardView = new ImageView[cardDrawableIdList.length];
	
	ItfEvent event;
	
	public int getCardSelectAreaId(int idx) {
		return cardSelectAreaIdList[idx];
	}
	
	public int getCardSelectResId(int idx) {
		return cardDrawableIdList[idx];
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		event = (ItfEvent) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.cardlist, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		Context context = getActivity();
		for (int i = 0; i < cardDrawableIdList.length; i++) {
			ivCardView[i] = new ImageView(context);
			final int idx = i;
			ivCardView[i].setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					event.cardSelect(idx);
				}
			});
		}
		
		View fragView = getView();
		LinearLayout[] llCardSelectAreaList = new LinearLayout[cardSelectAreaIdList.length];
		for (int i = 0; i < cardSelectAreaIdList.length; i++) {
			llCardSelectAreaList[i] = (LinearLayout) fragView.findViewById(cardSelectAreaIdList[i]);
		} 
		
		for (int i = 0; i < ivCardView.length; i++) {
			ivCardView[i].setImageResource(cardDrawableIdList[i]);
			llCardSelectAreaList[i].addView(ivCardView[i]);
		}
				
	}
}
