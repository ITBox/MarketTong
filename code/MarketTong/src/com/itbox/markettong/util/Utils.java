package com.itbox.markettong.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class Utils {

	public static void nullGone(TextView view, String text) {
		if (TextUtils.isEmpty(text)) {
			view.setVisibility(View.GONE);
		} else {
			view.setText(text);
		}
	}
}
