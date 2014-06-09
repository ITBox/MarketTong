package com.itbox.markettong.fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.itbox.markettong.R;
import com.itbox.markettong.SettingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MimeFragment extends BaseFragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_mime, null);
		ButterKnife.inject(this, layout);
		return layout;
	}
	
    @OnClick({R.id.mime_setting})
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mime_setting:
			startActivity(new Intent(mActThis, SettingActivity.class));
			mActThis.overridePendingTransition(R.anim.open_next, R.anim.close_main);
			break;

		default:
			break;
		}
	}
	
}
