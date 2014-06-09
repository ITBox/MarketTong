package com.itbox.markettong.dialog;


import com.itbox.markettong.R;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

public class DialogMessage extends DialogFragment {

	private TextView mTitle;

	public static DialogMessage newIntance() {
		DialogMessage dialog = new DialogMessage();
		return dialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Dialog dialog = new Dialog(getActivity(), R.style.dialog);
		View view = View.inflate(getActivity(), R.layout.loading, null);
		dialog.setContentView(view);
		mTitle = (TextView) view.findViewById(R.id.loading_tv);
		return dialog;
	}
    
    public void setMessage(String msg){
    	mTitle.setText(msg);
    }

}
