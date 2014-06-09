package com.itbox.markettong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
	
    protected BaseActivity mActThis;
    
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mActThis = this;
	}
	
	protected void startActivity(Class<? extends Activity> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
	}
}
