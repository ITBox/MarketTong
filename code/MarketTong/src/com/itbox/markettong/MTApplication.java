package com.itbox.markettong;

import com.activeandroid.ActiveAndroid;

import android.app.Application;

public class MTApplication extends Application {
	private static MTApplication mInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		// 初始化ActiveAndroid
		ActiveAndroid.initialize(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ActiveAndroid.dispose();
	}

	public static MTApplication getInstance() {
		return mInstance;
	}
}
