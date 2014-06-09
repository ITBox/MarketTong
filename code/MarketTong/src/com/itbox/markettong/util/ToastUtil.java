package com.itbox.markettong.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	public static void showMsg(Context context, String msg) {
		// TODO Auto-generated method stub
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void showMsg(Context context, int msg) {
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
