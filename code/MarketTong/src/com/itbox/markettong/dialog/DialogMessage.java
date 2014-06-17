package com.itbox.markettong.dialog;


import com.itbox.markettong.R;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

public class DialogMessage extends DialogFragment {

	private Dialog dialog;
    private String title;
    
	public static DialogMessage newIntance() {
		DialogMessage dialog = new DialogMessage();
		return dialog;
	}
   
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		dialog = new Dialog(getActivity(), R.style.dialog);
		View view = View.inflate(getActivity(), R.layout.loading_meaasge, null);
		dialog.setContentView(view);
		TextView mTitle = (TextView) view.findViewById(R.id.loading_tv);
		mTitle.setText(title);
		return dialog;
	}
    
    public void setMessage(String msg){
    	title = msg;
    }
    
    public void dissmissDialog () {
    	if (dialog != null) {
    		dialog.dismiss();
    		dialog = null;
    	}
    }
}
