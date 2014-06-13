package com.itbox.markettong.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class IntentUtil {

	public static void callPhone(Context ctx, String phoneNum) {
		if(TextUtils.isEmpty(phoneNum)) {
			ToastUtil.showMsg(ctx, "用户号码为空，不能拨叫");
		} else {
			Intent intent = new Intent();
			// 系统默认的action，用来打开默认的电话界面
			intent.setAction(Intent.ACTION_CALL);
			// 需要拨打的号码
			intent.setData(Uri.parse("tel:" + phoneNum));
			ctx.startActivity(intent);
		}
	}
}
