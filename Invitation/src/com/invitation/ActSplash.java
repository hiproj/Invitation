package com.invitation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


public class ActSplash extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);		
		AlphaAnimation ani = new AlphaAnimation(1.0f, 1.0f);
		// ani.setDuration(2500);
		// findViewById(R.id.textView1).startAnimation(ani);
		
		ani.setAnimationListener(new AnimationListener(){
			
			@Override
			public void onAnimationStart(Animation animation){}
			
			@Override
			public void onAnimationRepeat(Animation animation){}
			
			@Override
			public void onAnimationEnd(Animation animation){
				startActivity(new Intent(ActSplash.this, ActCardSelect.class));
				finish();
			}
		});
	}
}
