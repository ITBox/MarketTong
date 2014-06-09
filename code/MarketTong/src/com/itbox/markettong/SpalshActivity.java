package com.itbox.markettong;

import com.itbox.markettong.shimmer.Shimmer;
import com.itbox.markettong.shimmer.ShimmerTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.SuppressLint;
import android.os.Bundle;

@SuppressLint("NewApi")
public class SpalshActivity extends BaseActivity {
	@InjectView(R.id.tv)
	ShimmerTextView tv;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_spalsh);
		ButterKnife.inject(mActThis);
		final Shimmer shimmer = new Shimmer();
		shimmer.setRepeatCount(2).setDuration(1000).setDirection(Shimmer.ANIMATION_DIRECTION_LTR).setAnimatorListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				shimmer.cancel();
				startActivity(MainActivity.class);
				finish();
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		}).start(tv);
	}
}
