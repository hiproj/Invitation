package com.invitation;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
	}
	
	ArrayList<Fragment> workList = new ArrayList<Fragment>(); 
	
	Fragment	fragCardSelect	= new FragCardSelect(), fragSplash = new FragSplash(), fragInput = new FragInput();
	
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
		((FragInput) fragInput).setSelctCardIndex(index);
		switchFrag(fragInput);
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
}
