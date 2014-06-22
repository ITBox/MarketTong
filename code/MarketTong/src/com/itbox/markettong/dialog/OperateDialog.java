package com.itbox.markettong.dialog;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.itbox.markettong.R;
import com.itbox.markettong.util.DimenUtil;

public class OperateDialog extends StandOutWindow {

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return "XST";
	}

	@Override
	public int getAppIcon() {
		// TODO Auto-generated method stub
		return R.drawable.ic_launcher;
	}

	@Override
	public void createAndAttachView(final int id, FrameLayout frame) {
		// TODO Auto-generated method stub
        View.inflate(getApplicationContext(), R.layout.dialog_action, frame).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				close(id);
			}
		});;
	}

	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		return new StandOutLayoutParams(id, wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight() + DimenUtil.getStatusBarHeight(getApplicationContext()),
				StandOutLayoutParams.LEFT, StandOutLayoutParams.BOTTOM);
	}

	@Override
	public int getFlags(int id) {
		return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
	}
    @Override
    public String getPersistentNotificationTitle(int id) {
    	return "销售通提醒您";
    }

	@Override
	public Intent getPersistentNotificationIntent(int id) {
		return StandOutWindow.getCloseIntent(this, OperateDialog.class, id);
	}
}
