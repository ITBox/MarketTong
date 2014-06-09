package com.itbox.markettong;

import android.os.Bundle;

public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mActThis.overridePendingTransition (R.anim.open_main, R.anim.close_next);
	}
}
