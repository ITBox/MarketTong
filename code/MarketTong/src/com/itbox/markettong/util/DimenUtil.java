package com.itbox.markettong.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;

public class DimenUtil {

	public static int dp2px(Context context, int px) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
				context.getResources().getDisplayMetrics());
	}

	public static int sp2px(Context context, int px) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px,
				context.getResources().getDisplayMetrics());
	}

	public static int getFontHeight(float fontSize) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		FontMetrics fm = paint.getFontMetrics();
		return (int) Math.ceil(fm.descent - fm.top) + 2;
	}

	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	public static float getBaseY(Context context, MotionEvent e) {
		return e.getRawY() - e.getY() - getStatusBarHeight(context);
	}

	public static float getBaseX(Context context, MotionEvent e) {
		return e.getRawX() - e.getX();
	}
}
