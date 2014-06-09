package com.itbox.markettong.fragment;

import com.itbox.markettong.R;
import com.itbox.markettong.SearchActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ContactFragment extends BaseFragment {
    @InjectView(R.id.search_ll)
    LinearLayout mSearchLL;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.fragment_contact, null);
		ButterKnife.inject(this, layout);
		return layout;
	}
	
	@OnClick(R.id.search_ll)
	public void clickSearch() {
		startActivity(new Intent(mActThis, SearchActivity.class));
	}
}
