package com.invitation;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.invitation.R;

public class ActMain extends FragmentActivity implements ItfEvent{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// startActivity(new Intent(this, ActSplash.class));
		// finish();
		//
		setContentView(R.layout.frag);
		switchFrag(fragSplash);
		workList.remove(0);
		//
		// switchFrag(fragMoveTest);
	}
	
	ArrayList<Fragment>	workList		= new ArrayList<Fragment>();
	FragCardList		fragCardSelect	= new FragCardList();
	FragSplash			fragSplash		= new FragSplash();
	// FragInput			fragInput		= new FragInput();
	FragMoveTest		fragMoveTest	= new FragMoveTest();
	
	public void switchFrag(Fragment frag){
		workList.add(frag);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.flFrag, frag);
		// fragmentTransaction.add(R.id.flFrag, (isFrag) ? frag1 : frag2);
		fragmentTransaction.commit();
	}
	
	@Override
	public void cardSelect(final int index){
		((FragMoveTest) fragMoveTest).setSelctCardIndexAndResId(index, ((FragCardList) fragCardSelect).getCardSelectResId(index));
		switchFrag(fragMoveTest);
	}
	
	@Override
	public void splashEnd(){
		switchFrag(fragCardSelect);
	}
	
	@Override
	public void onBackPressed(){
		if (workList.size() <= 1) {
			finish();
		} else {
			workList.remove(workList.size() - 1);
			switchFrag(workList.get(workList.size() - 1));
			workList.remove(workList.size() - 1);
		}
	}
	
	@Override
	public void setColorChange(int color){
		switch (styleSelectId) {
			case R.id.tvSetTextColor:
				fragMoveTest.setTextColor(color);
			break;
			case R.id.tvSetTextColorStroke:
				fragMoveTest.setTextColorStroke(color);
			break;
		}
	}
	
	@Override
	public void startStyleChange(){
		DlgTextEditMenu dlgTextEditMenu = new DlgTextEditMenu(this, this);
		dlgTextEditMenu.setTitle("수정");
		dlgTextEditMenu.show();
	}
	
	private int	styleSelectId	= -1;
	
	@Override
	public void selectTextStyle(int id){
		styleSelectId = id;
		TVStroke tvTarget = fragMoveTest.getFlTextMove().tvTarget;
		switch (styleSelectId) {
			case R.id.tvSetTextSize:
				DlgTextSizePick dlgTextSizePick = new DlgTextSizePick(this, this, tvTarget);
				dlgTextSizePick.setTitle("글자 크기 선택 / 현재 : " + tvTarget.getTextSize());
				dlgTextSizePick.show();
			break;
			case R.id.tvSetTextColor:
				DlgColorPick dlgTextColorPick = new DlgColorPick(this, this);
				dlgTextColorPick.setTitle("글자색 선택");
				dlgTextColorPick.show();
			break;
			case R.id.tvSetTextColorStroke:
				DlgColorPick dlgStrokeColorPick = new DlgColorPick(this, this);
				dlgStrokeColorPick.setTitle("글자 테두리색 선택");
				dlgStrokeColorPick.show();
			break;
			case R.id.tvSetText:
				DlgTextSet dlgSetText = new DlgTextSet(this, this, tvTarget);
				dlgSetText.setTitle("글자 입력");
				dlgSetText.show();
			break;
		}
	}
	
	@Override
	public void setTextSize(int size){
		fragMoveTest.setTextSize(size);
	}
}
