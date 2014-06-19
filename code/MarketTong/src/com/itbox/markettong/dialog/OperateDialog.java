package com.itbox.markettong.dialog;

import com.itbox.markettong.R;

import android.app.Notification;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import wei.mark.standout.StandOutWindow;
import wei.mark.standout.StandOutWindow.StandOutLayoutParams;
import wei.mark.standout.ui.Window;

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
	public void createAndAttachView(int id, FrameLayout frame) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_action, frame, true);

         
	}

	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		return new StandOutLayoutParams(id, StandOutLayoutParams.MATCH_PARENT, StandOutLayoutParams.WRAP_CONTENT,
				StandOutLayoutParams.LEFT, StandOutLayoutParams.BOTTOM);
	}

	@Override
	public int getFlags(int id) {
		// TODO Auto-generated method stub
		return super.getFlags(id);
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
